package de.fhg.iais.roberta.syntax.codegen.arduino.arduino;

import java.util.ArrayList;

import de.fhg.iais.roberta.components.ConfigurationBlockType;
import de.fhg.iais.roberta.components.UsedConfigurationBlock;
import de.fhg.iais.roberta.components.arduino.ArduinoConfiguration;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.action.display.ClearDisplayAction;
import de.fhg.iais.roberta.syntax.action.display.ShowPictureAction;
import de.fhg.iais.roberta.syntax.action.display.ShowTextAction;
import de.fhg.iais.roberta.syntax.action.light.LightAction;
import de.fhg.iais.roberta.syntax.action.light.LightStatusAction;
import de.fhg.iais.roberta.syntax.action.motor.CurveAction;
import de.fhg.iais.roberta.syntax.action.motor.DriveAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorDriveStopAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorGetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorOnAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorSetPowerAction;
import de.fhg.iais.roberta.syntax.action.motor.MotorStopAction;
import de.fhg.iais.roberta.syntax.action.motor.TurnAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayFileAction;
import de.fhg.iais.roberta.syntax.action.sound.PlayNoteAction;
import de.fhg.iais.roberta.syntax.action.sound.SayTextAction;
import de.fhg.iais.roberta.syntax.action.sound.SetLanguageAction;
import de.fhg.iais.roberta.syntax.action.sound.ToneAction;
import de.fhg.iais.roberta.syntax.action.sound.VolumeAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.ExternalLedOffAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.ExternalLedOnAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOffAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOnAction;
import de.fhg.iais.roberta.syntax.check.hardware.arduino.arduino.UsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.syntax.codegen.arduino.ArduinoVisitor;
import de.fhg.iais.roberta.syntax.expressions.arduino.RgbColor;
import de.fhg.iais.roberta.syntax.lang.blocksequence.MainTask;
import de.fhg.iais.roberta.syntax.sensor.generic.BrickSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.ColorSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.LightSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.MoistureSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.UltrasonicSensor;
import de.fhg.iais.roberta.syntax.sensor.generic.VoltageSensor;
import de.fhg.iais.roberta.util.dbc.DbcException;
import de.fhg.iais.roberta.visitor.AstVisitor;
import de.fhg.iais.roberta.visitors.arduino.ArduinoAstVisitor;

/**
 * This class is implementing {@link AstVisitor}. All methods are implemented and they append a human-readable C representation of a phrase to a
 * StringBuilder. <b>This representation is correct C code for Arduino.</b> <br>
 */
public class CppVisitor extends ArduinoVisitor implements ArduinoAstVisitor<Void> {
    private final boolean isTimerSensorUsed;

    /**
     * Initialize the C++ code generator visitor.
     *
     * @param programPhrases to generate the code from
     * @param indentation to start with. Will be incr/decr depending on block structure
     */
    private CppVisitor(ArduinoConfiguration brickConfiguration, ArrayList<ArrayList<Phrase<Void>>> phrases, int indentation) {
        super(phrases, indentation);
        UsedHardwareCollectorVisitor codePreprocessVisitor = new UsedHardwareCollectorVisitor(phrases, brickConfiguration);
        this.usedVars = codePreprocessVisitor.getVisitedVars();
        this.usedConfigurationBlocks = codePreprocessVisitor.getUsedConfigurationBlocks();
        //TODO: fix how the timer is detected for all robots
        this.isTimerSensorUsed = codePreprocessVisitor.isTimerSensorUsed();
        this.loopsLabels = codePreprocessVisitor.getloopsLabelContainer();
    }

    /**
     * factory method to generate C++ code from an AST.<br>
     *
     * @param brickConfiguration
     * @param programPhrases to generate the code from
     * @param withWrapping if false the generated code will be without the surrounding configuration code
     */
    public static String generate(ArduinoConfiguration brickConfiguration, ArrayList<ArrayList<Phrase<Void>>> programPhrases, boolean withWrapping) {
        CppVisitor astVisitor = new CppVisitor(brickConfiguration, programPhrases, withWrapping ? 1 : 0);
        astVisitor.generateCode(withWrapping);
        return astVisitor.sb.toString();
    }

    @Override
    public Void visitShowPictureAction(ShowPictureAction<Void> showPictureAction) {
        return null;
    }

    @Override
    public Void visitShowTextAction(ShowTextAction<Void> showTextAction) {
        //this.sb.append("lcd.setCursor(0," + showTextAction.getX() + ");");
        //nlIndent();
        //this.sb.append("lcd.print(\"");
        //showTextAction.visit(this);
        //this.sb.append("\");");
        return null;
    }

    @Override
    public Void visitClearDisplayAction(ClearDisplayAction<Void> clearDisplayAction) {
        return null;
    }

    @Override
    public Void visitVolumeAction(VolumeAction<Void> volumeAction) {
        return null;
    }

    @Override
    public Void visitSetLanguageAction(SetLanguageAction<Void> setLanguageAction) {
        return null;
    }

    @Override
    public Void visitSayTextAction(SayTextAction<Void> sayTextAction) {
        return null;
    }

    @Override
    public Void visitLightAction(LightAction<Void> lightAction) {
        return null;

    }

    @Override
    public Void visitLightStatusAction(LightStatusAction<Void> lightStatusAction) {
        return null;
    }

    @Override
    public Void visitPlayFileAction(PlayFileAction<Void> playFileAction) {
        return null;
    }

    @Override
    public Void visitToneAction(ToneAction<Void> toneAction) {
        return null;
    }

    @Override
    public Void visitPlayNoteAction(PlayNoteAction<Void> playNoteAction) {
        return null;
    }

    @Override
    public Void visitMotorOnAction(MotorOnAction<Void> motorOnAction) {
        return null;
    }

    @Override
    public Void visitMotorSetPowerAction(MotorSetPowerAction<Void> motorSetPowerAction) {
        return null;
    }

    @Override
    public Void visitMotorGetPowerAction(MotorGetPowerAction<Void> motorGetPowerAction) {
        return null;
    }

    @Override
    public Void visitMotorStopAction(MotorStopAction<Void> motorStopAction) {
        return null;
    }

    @Override
    public Void visitDriveAction(DriveAction<Void> driveAction) {
        return null;
    }

    @Override
    public Void visitCurveAction(CurveAction<Void> curveAction) {
        return null;
    }

    @Override
    public Void visitTurnAction(TurnAction<Void> turnAction) {
        return null;
    }

    @Override
    public Void visitMotorDriveStopAction(MotorDriveStopAction<Void> stopAction) {
        return null;
    }

    @Override
    public Void visitLightSensor(LightSensor<Void> lightSensor) {
        this.sb.append("analogRead(_eingang_" + lightSensor.getPort().getPortNumber() + ")");
        //this.sb.append("analogRead(eingang_" + lightSensor.getBlockName() + ")");
        return null;
    }

    @Override
    public Void visitColorSensor(ColorSensor<Void> colorSensor) {
        return null;
    }

    @Override
    public Void visitBrickSensor(BrickSensor<Void> button) {
        return null;
    }

    @Override
    public Void visitVoltageSensor(VoltageSensor<Void> relay) {

        return null;
    }

    @Override
    public Void visitUltrasonicSensor(UltrasonicSensor<Void> ultrasonicSensor) {
        this.sb.append("pulseIn(_echo_" + ultrasonicSensor.getPort().getPortNumber() + ", HIGH)*_signalToDistance");
        return null;
    }

    @Override
    public Void visitMoistureSensor(MoistureSensor<Void> moistureSensor) {
        this.sb.append("analogRead(_moisturePin_" + moistureSensor.getPort().getPortNumber() + ")");
        return null;
    }

    @Override
    public Void visitMainTask(MainTask<Void> mainTask) {
        decrIndentation();
        mainTask.getVariables().visit(this);
        nlIndent();
        generateConfigurationVariables();
        if ( this.isTimerSensorUsed ) {
            nlIndent();
            this.sb.append("unsigned long __time = millis(); \n");
        }
        incrIndentation();
        generateUserDefinedMethods();
        this.sb.append("\n \n void setup() \n");
        this.sb.append("{");
        nlIndent();
        this.sb.append("Serial.begin(9600); ");
        nlIndent();
        this.generateConfigurationSetup();
        generateUsedVars();
        this.sb.append("\n}\n");
        this.sb.append("\n").append("void loop() \n");
        this.sb.append("{");
        return null;
    }

    @Override
    protected void generateProgramPrefix(boolean withWrapping) {
        if ( !withWrapping ) {
            return;
        }
        this.sb.append("#include <math.h> \n");
    }

    @Override
    protected void generateProgramSuffix(boolean withWrapping) {
        if ( withWrapping ) {
            this.sb.append("\n}\n");
        }
    }

    private void generateConfigurationSetup() {
        for ( UsedConfigurationBlock usedConfigurationBlock : this.usedConfigurationBlocks ) {
            switch ( (ConfigurationBlockType) usedConfigurationBlock.getType() ) {
                case ULTRASONIC:
                    this.sb.append("pinMode(_trigger_" + usedConfigurationBlock.getBlockName() + ", OUTPUT);");
                    nlIndent();
                    this.sb.append("pinMode(_echo_" + usedConfigurationBlock.getBlockName() + ", INPUT);");
                    nlIndent();
                    break;
                case MOISTURE:
                    break;
                case LIGHT:
                    break;
                default:
                    throw new DbcException("Sensor is not supported: " + usedConfigurationBlock.getType());
            }
        }
    }

    private void generateConfigurationVariables() {
        for ( UsedConfigurationBlock usedConfigurationBlock : this.usedConfigurationBlocks ) {
            switch ( (ConfigurationBlockType) usedConfigurationBlock.getType() ) {
                case ULTRASONIC:
                    this.sb.append("int _trigger_" + usedConfigurationBlock.getBlockName() + " = ").append(usedConfigurationBlock.getPins().get(0)).append(";");
                    nlIndent();
                    this.sb.append("int _echo_" + usedConfigurationBlock.getBlockName() + " = ").append(usedConfigurationBlock.getPins().get(1)).append(";");
                    nlIndent();
                    this.sb.append(
                        "double _signalToDistance = 0.03432/2; //Nun berechnet man die Entfernung in Zentimetern. Man teilt zunächst die Zeit durch zwei (Weil man ja nur eine Strecke berechnen möchte und nicht die Strecke hin- und zurück). Den Wert multipliziert man mit der Schallgeschwindigkeit in der Einheit Zentimeter/Mikrosekunde und erhält dann den Wert in Zentimetern.");
                    nlIndent();
                    break;
                case MOISTURE:
                    this.sb.append("int _moisturePin_" + usedConfigurationBlock.getBlockName() + " = ").append(usedConfigurationBlock.getPins().get(0)).append(
                        ";");
                    nlIndent();
                    break;
                case LIGHT:
                    this.sb.append("int _eingang_" + usedConfigurationBlock.getBlockName() + " = ").append(usedConfigurationBlock.getPins().get(0)).append(";");
                    nlIndent();
                    break;
                default:
                    throw new DbcException("Sensor is not supported: " + usedConfigurationBlock.getType());
            }
        }
    }

    @Override
    public Void visitLedOnAction(LedOnAction<Void> ledOnAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitLedOffAction(LedOffAction<Void> ledOffAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitExternalLedOnAction(ExternalLedOnAction<Void> externalLedOnAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitExternalLedOffAction(ExternalLedOffAction<Void> externalLedOffAction) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitRgbColor(RgbColor<Void> rgbColor) {
        // TODO Auto-generated method stub
        return null;
    }

}
