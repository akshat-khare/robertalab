package de.fhg.iais.roberta.components.arduino;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fhg.iais.roberta.components.Actor;
import de.fhg.iais.roberta.components.Configuration;
import de.fhg.iais.roberta.components.Sensor;
import de.fhg.iais.roberta.inter.mode.action.IActorPort;
import de.fhg.iais.roberta.inter.mode.sensor.ISensorPort;
import de.fhg.iais.roberta.mode.actors.arduino.botnroll.ActorPort;
import de.fhg.iais.roberta.util.Pair;

public class ArduinoConfiguration extends Configuration {

    public ArduinoConfiguration(Map<IActorPort, Actor> actors, Map<ISensorPort, Sensor> sensors) {
        super(actors, sensors, 0, 0);

    }

    /**
     * @return text which defines the brick configuration
     */
    @Override
    public String generateText(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("board Arduino Uno ").append(name).append(" {\n");
        generateSensors(sb);
        generateActors(sb);
        sb.append("}");
        return sb.toString();
    }

    private void generateSensors(StringBuilder sb) {
        if ( !this.sensors.isEmpty() ) {
            sb.append("  sensor port {\n");
            for ( ISensorPort port : this.sensors.keySet() ) {
                sb.append("    ").append(port.getPortNumber()).append(": ");
                String sensor = this.sensors.get(port).getType().toString();
                sb.append(sensor.toLowerCase()).append(";\n");
            }
            sb.append("  }\n");
        }
    }

    private void generateActors(StringBuilder sb) {
        if ( !this.actors.isEmpty() ) {
            sb.append("  actor port {\n");
            for ( IActorPort port : this.actors.keySet() ) {
                sb.append("    ").append(port.toString()).append(": ");
                Actor actor = this.actors.get(port);
                sb.append(";\n");
            }
            sb.append("  }\n");
        }
    }

    /**
     * This class is a builder of {@link Configuration}
     */
    public static class Builder extends Configuration.Builder<Builder> {
        private final Map<IActorPort, Actor> actorMapping = new TreeMap<>();
        private final Map<ISensorPort, Sensor> sensorMapping = new TreeMap<>();

        /**
         * Add actor to the {@link Configuration}
         *
         * @param port on which the component is connected
         * @param actor we want to connect
         * @return
         */
        @Override
        public Builder addActor(IActorPort port, Actor actor) {
            this.actorMapping.put(port, actor);
            return this;
        }

        /**
         * Client must provide list of {@link Pair} ({@link ActorPort} and {@link Actor})
         *
         * @param actors we want to connect to the brick configuration
         * @return
         */
        @Override
        public Builder addActors(List<Pair<IActorPort, Actor>> actors) {
            for ( Pair<IActorPort, Actor> pair : actors ) {
                this.actorMapping.put(pair.getFirst(), pair.getSecond());
            }
            return this;
        }

        /**
         * Add sensor to the {@link Configuration}
         *
         * @param port on which the component is connected
         * @param component we want to connect
         * @return
         */

        @Override
        public Builder addSensor(ISensorPort port, Sensor sensor) {
            this.sensorMapping.put(port, sensor);
            return this;
        }

        /**
         * Client must provide list of {@link Pair} ({@link SensorPort} and {@link Sensor})
         *
         * @param sensors we want to connect to the brick configuration
         * @return
         */
        @Override
        public Builder addSensors(List<Pair<ISensorPort, Sensor>> sensors) {
            for ( Pair<ISensorPort, Sensor> pair : sensors ) {
                this.sensorMapping.put(pair.getFirst(), pair.getSecond());
            }
            return this;
        }

        @Override
        public Configuration build() {
            return new ArduinoConfiguration(this.actorMapping, this.sensorMapping);
        }

        @Override
        public String toString() {
            return "Builder [actorMapping=" + this.actorMapping + ", sensorMapping=" + this.sensorMapping + "]";
        }

    }

}
