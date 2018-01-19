package de.fhg.iais.roberta.syntax.check.hardware.arduino.botnroll;

import java.util.ArrayList;

import de.fhg.iais.roberta.components.arduino.BotNrollConfiguration;
import de.fhg.iais.roberta.syntax.Phrase;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.ExternalLedOffAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.ExternalLedOnAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOffAction;
import de.fhg.iais.roberta.syntax.actors.arduino.mbot.LedOnAction;
import de.fhg.iais.roberta.syntax.check.hardware.RobotUsedHardwareCollectorVisitor;
import de.fhg.iais.roberta.syntax.expressions.arduino.RgbColor;
import de.fhg.iais.roberta.visitors.arduino.ArduinoAstVisitor;

/**
 * This visitor collects information for used actors and sensors in blockly program.
 *
 * @author eovchinnikova
 */
public class UsedHardwareCollectorVisitor extends RobotUsedHardwareCollectorVisitor implements ArduinoAstVisitor<Void> {

    public UsedHardwareCollectorVisitor(ArrayList<ArrayList<Phrase<Void>>> phrasesSet, BotNrollConfiguration configuration) {
        super(configuration);
        check(phrasesSet);
    }

    @Override
    public Void visitLedOnAction(LedOnAction<Void> ledOnAction) {
        return null;
    }

    @Override
    public Void visitLedOffAction(LedOffAction<Void> ledOffAction) {
        return null;
    }

    @Override
    public Void visitExternalLedOnAction(ExternalLedOnAction<Void> externalLedOnAction) {
        return null;
    }

    @Override
    public Void visitExternalLedOffAction(ExternalLedOffAction<Void> externalLedOffAction) {
        return null;
    }

    @Override
    public Void visitRgbColor(RgbColor<Void> rgbColor) {
        return null;
    }

}
