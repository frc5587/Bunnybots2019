package frc.robot;

import org.frc5587.lib.pid.FPID;
import org.frc5587.lib.pid.PIDVA;

public class Constants {
    
    public static final int K_TIMEOUT_MS = 10;
    public static final double K_V_COMP_SATURATION = 12.0;
    
    public static final class Drive {
        // set to zero to skip waiting for confirmation, set to nonzero to wait and
        // report to DS if action fails
        public static final int K_TIMEOUT_MS = 10;

        // fix this
        public static final double K_MAX_VELOCITY = 2500; // measured in STU

        public static final int MIN_BUFFER_COUNT = 10;

        // fix this
        public static final int SPARK_UNITS_PER_INCH = 0;

        // Safety limits
        public static final float MIN_PERCENT_OUT = 0, MAX_PERCENT_BW = 1, MAX_PERCENT_FW = 1;

        // Pathfinder constants
        public static final double GYRO_KP = 0.00;
        public static final int WHEEL_DIAMETER = 6;

        public static final PIDVA PATHFINDER_PIDVA_LEFT = new PIDVA(0.04, 0.0, 0.0, 0.000327 * SPARK_UNITS_PER_INCH / 10f,
                0.0001 * SPARK_UNITS_PER_INCH / 10f);
        public static final PIDVA PATHFINDER_PIDVA_RIGHT = new PIDVA(0.04, 0.0, 0.0, 0.000317 * SPARK_UNITS_PER_INCH / 10f,
                0.0001 * SPARK_UNITS_PER_INCH / 10f);

        // Turn controller
        public static final int GYRO_HISTORY_LENGTH = 50;
        public static final double LPF_PERCENT = 1;
        public static final double TOLERANCE_DEGREES = 2.0;
        public static final FPID TURN_FPID = new FPID(
                0,  // kF
                0.03, // kP
                0, // kI
                0  // kD
        );

         // FPID Constants
         // FIX VALUES
         public static final double[] LEFT_PIDS_DOUBLE = {
            (1 / 4284) * 1023, // kF
            0.42752, // kP
            0.001, // kI
            8.5504 // kD
        };

        public static final double[] RIGHT_PIDS_DOUBLE = {
                (1 / 4553) * 1023, // kF
                0.37584, // kP
                0.001, // kI
                7.5168 // kD
        };

        public static final FPID LEFT_PIDS = new FPID(
            (1 / 4284) * 1023, // kF
            0.42752, // kP
            0.001, // kI
            8.5504 // kD
        );

        public static final FPID RIGHT_PIDS = new FPID(
                (1 / 4553) * 1023, // kF
                0.37584, // kP
                0.001, // kI
                7.5168 // kD
        );

        public static final int MAX_VELOCITY = 2269, MAX_ACCELERATION = 2 * MAX_VELOCITY;
        public static final int MIN_VELOCITY = 0;

        public static final double HOLD_VOLTAGE = 0.05;

        public static final int SMART_MOTION_SLOT = 0;
        
        public static final int GEAR_RATIO = 30;

        public static final double ALLOWED_ERR = 1;

        public static final double V_COMP_SATURATION = 12.0;

    }
}