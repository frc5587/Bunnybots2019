package frc.robot;

public class Constants {
    
    public static final int K_TIMEOUT_MS = 10;
    public static final double K_V_COMP_SATURATION = 12.0;
    
    public static final class Drive {
        // set to zero to skip waiting for confirmation, set to nonzero to wait and
        // report to DS if action fails
        public static final int K_TIMEOUT_MS = 10;

        public static final double K_MAX_VELOCITY = 2500; // measured in STU

        public static final int MIN_BUFFER_COUNT = 10;

        // Safety limits
        public static final float MIN_PERCENT_OUT = 0, MAX_PERCENT_BW = 1, MAX_PERCENT_FW = 1;

        // Pathfinder constants
        public static final double GYRO_KP = 0.00;
        public static final int WHEEL_DIAMETER = 6;

        public static final int MAX_VELOCITY = 2269, MAX_ACCELERATION = 2 * MAX_VELOCITY;
        public static final int MIN_VELOCITY = 0;
        
        public static final int GEAR_RATIO = 30;

        public static final double ALLOWED_ERR = 1;

        public static final double V_COMP_SATURATION = 12.0;

    }
}