define([ 'exports', 'util', 'log', 'message', 'jquery', 'robot.controller', 'guiState.controller', 'guiState.model', 'socket.io' ], function(exports, UTIL,
        LOG, MSG, $, ROBOT_C, GUISTATE_C, GUISTATE, IO) {

    var portList = [];
    var vendorList = [];
    var productList = [];
    var system;
    var cmd;
    var port;
    var robotList = [];

    function init() {
        robotSocket = GUISTATE_C.getSocket()
        if (robotSocket == null || GUISTATE_C.getIsAgent() == false) {
            robotSocket = IO('ws://localhost:8991/');
            GUISTATE_C.setSocket(robotSocket);
            //GUISTATE.robot.socket = IO('ws://localhost:8991/');
            GUISTATE_C.setIsAgent(true);
            $('#menuConnect').parent().addClass('disabled');
            //so it would not be active when the socket cannot connect
            /*
             * $('#head-navi-icon-robot').removeClass('error');
             * $('#head-navi-icon-robot').removeClass('busy');
             * $('#head-navi-icon-robot').removeClass('wait'); if
             * (GUISTATE.gui.blocklyWorkspace) {
             * GUISTATE.gui.blocklyWorkspace.robControls.disable('runOnBrick'); }
             * $('#menuRunProg').parent().addClass('disabled');
             * $('#menuConnect').parent().addClass('disabled');
             */
            robotSocket.on('connect_error', function(err) {
                GUISTATE_C.setIsAgent(false);
                console.log('Error connecting to server');
            });

            robotSocket.on('connect', function() {
                console.log('connect');
                robotSocket.emit('command', 'log on');
                console.log('listed');
                GUISTATE_C.setIsAgent(true);
                window.setInterval(function() {
                    portList = [];
                    vendorList = [];
                    productList = [];
                    robotList = [];
                    robotSocket.emit('command', 'list');
                    console.log('refreshed robot ports');
                }, 3000);
            });

            /*
             * Vendor and Product IDs for some robots Botnroll: /dev/ttyUSB0,
             * VID: 0x10c4, PID: 0xea60 Mbot: /dev/ttyUSB0, VID: 0x1a86, PID:
             * 0x7523 ArduinoUno: /dev/ttyACM0, VID: 0x2a03, PID: 0x0043
             */
            robotSocket.on('message', function(data) {
                if (data.includes('"Network": false')) {
                    var robot;
                    jsonObject = JSON.parse(data);
                    jsonObject['Ports'].forEach(function(port) {
                        if (GUISTATE_C.getVendor() === port['VendorID'].toLowerCase()) {
                            portList.push(port['Name']);
                            vendorList.push(port['VendorID']);
                            productList.push(port['ProductID']);
                            console.log(port['VendorID'].toUpperCase());
                            switch (port['VendorID'].toLowerCase()) {
                            case '0x10c4':
                                robot = 'Bot\'n Roll';
                                break;
                            case '0x1a86':
                                robot = 'MBot';
                                break;
                            case '0x2a03':
                                robot = 'Arduino Uno';
                                break;
                            default:
                                robot = 'Unknown robot';
                            }
                            robotList.push(robot);
                        }
                    });
                    GUISTATE_C.setIsAgent(true);

                    robotSocket.on('connect_error', function(err) {
                        GUISTATE_C.setIsAgent(false);
                        $('#menuConnect').parent().removeClass('disabled');
                        console.log('Error connecting to server');
                    });
                    console.log(GUISTATE_C.getRobotPort());
                    if (portList.indexOf(GUISTATE_C.getRobotPort()) < 0) {
                        if (GUISTATE_C.getRobotPort() != "") {
                            MSG.displayMessage(Blockly.Msg["MESSAGE_ROBOT_DISCONNECTED"], 'POPUP', '');
                        }
                        GUISTATE_C.setRobotPort("");
                        //console.log("port is not in the list");
                    }
                    if (portList.length == 1) {
                        ROBOT_C.setPort(portList[0]);
                    }
                    GUISTATE_C.updateMenuStatus();
                    //console.log(new Date() + " " + portList);
                    //console.log(new Date() + " " + vendorList);
                    //console.log(new Date() + " " + productList);
                } else if (data.includes('OS')) {
                    jsonObject = JSON.parse(data);
                    system = jsonObject['OS'];
                    console.log(system);
                }
            });

            robotSocket.on('disconnect', function() {
            });

            robotSocket.on('error', function(err) {
                console.log("Socket.IO Error");
                console.log(err.stack);
            });
        }
    }

    exports.init = init;

    function closeConnection() {
        robotSocket = GUISTATE_C.getSocket()

        if (robotSocket != null) {
            robotSocket.disconnect();
            GUISTATE_C.setSocket(null);
        }
    }
    exports.closeConnection = closeConnection;

    function getPortList() {
        return portList;
    }
    exports.getPortList = getPortList;

    function getRobotList() {
        return robotList;
    }
    exports.getRobotList = getRobotList;

    function uploadProgram(programHex, robotPort) {
        var URL = 'http://localhost:8991/upload';
        var filename = GUISTATE_C.getProgramName() + '.hex';
        var fileContentHex = programHex;
        var port = robotPort;
        var board = 'arduino:avr:uno';
        console.log("uploading " + filename);
        var signature = "8ca56849f32e00f72e8a9a67360513761f8b25d25b9a0fd4b6bbc3eb68dfbbca1a8e40159456ef8c375186af9cdfaeb3ceabaa198a0313d0ab7f4ce67229381c3d84bd3b2632538957dab40d17f7bdc560cf82e540d51bf29f70f9ebee1abab1c0a18bdeb74e0d8b94b966744563251e0e868d4195719961ce0c5023c1f0a489";
        var commandLine = "\"{runtime.tools.avrdude.path}/bin/avrdude\" \"-C{runtime.tools.avrdude.path}/etc/avrdude.conf\" {upload.verbose} -patmega328p -carduino -P{serial.port} -b115200 -D \"-Uflash:w:{build.path}/{build.project_name}.hex:i\"";

        // signatureBob3 = "009de3ed2c8fbfaa5fa0b796f71a5f7b61081d82461dd73d626c288adeffd845fdd2eb1e801b4da5609fc9eb9c149d17e1d551b74313e698260a8e02436197b4bd0893232515609ab2a55b5d35232e0653f6f716f816e2acb81654c85f2fe1075f5c168804584a3e315df43d63c4c8762ab5fc618cf83b84cc9162d595379e17";
        // commandLineBob3 = "\"{runtime.tools.avrdude.path}/bin/avrdude\" \"-C{runtime.tools.avrdude.path}/etc/avrdude.conf\" {upload.verbose} -patmega88 -cavrisp2 -P{serial.port} -b38400 -D -e \"-Uflash:w:{build.path}/{build.project_name}.hex:i\"";

        var request = {
            'board' : board,
            'port' : port,
            'commandline' : commandLine,
            'signature' : signature,
            'hex' : fileContentHex,
            'filename' : filename,
            'extra' : {
                'auth' : {
                    'password' : null
                }
            },
            'wait_for_upload_port' : true,
            'use_1200bps_touch' : true,
            'network' : false,
            'params_verbose' : '-v',
            'params_quiet' : '-q -q',
            'verbose' : true
        }

        console.log(JSON.stringify(request));

        var JSONrequest = JSON.stringify(request);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", URL, true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 202) {
                console.log('Accepted for flashing');
            } else {
                console.log('something went wrong');
                console.log(xhr.readyState + ' ' + xhr.status);
            }
        }
        xhr.send(JSONrequest);
    }
    exports.uploadProgram = uploadProgram;

});
