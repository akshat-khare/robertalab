define([ 'require', 'exports', 'log', 'util', 'message', 'comm', 'userGroup.model', 'guiState.controller', 'blocks-msg', 'jquery', 'bootstrap-table' ], function(require, exports, LOG,
        UTIL, MSG, COMM, USERGROUP, GUISTATE_C, Blockly, $) {

    function init() {
        initEvents();
        LOG.info('init user group delete');
    }
    exports.init = init;

    function initEvents() {
        /**
         * Delete the user groups those were selected in a user group list
         */
        $('#doDeleteUserGroup').onWrap('click', function() {
            var userGroup = $("#confirmDeleteUserGroup").data('userGroup');
            for (var i = 0; i < userGroup.length; i++) {
<<<<<<< ad122ec816f8024ef6384122bf46e051c4480432
                var userName = userGroup[i][0];
=======
                var userName = userGroup[0][0];
>>>>>>> #384 copied old dashboard to current develop
                var groupName = GUISTATE_C.getGroupName();
                var resulting1;
                var resulting2;
                USERGROUP.deleteUserFromTheGroup(userName, groupName, function(resulting1, resulting2, result) {
                UTIL.response(resulting1);
                if (resulting1.rc === 'ok') {
                    MSG.displayInformation(resulting1, "MESSAGE_USER_GROUP_DELETED", resulting1.message, userName);
                    $('#userGroupList').find('button[name="refresh"]').trigger('click');
                    LOG.info('delete user "' + userName + "' from group '" + groupName + "'");
                }
            });
                
            }
            $('.modal').modal('hide');
        }), 'doDeleteUserGroup clicked';
    }
});
