package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANAnalog.AnalogMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.frc5587.lib.pathfinder.SparkAbstractDrive;

// import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI.Port;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drive extends SparkAbstractDrive implements PIDOutput {
  
    // private PIDController turnController;
    // private boolean turnEnabledFirstTime;
    
    public Drive() {
        super(new CANSparkMax(RobotMap.Drive.LEFT_ONE, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.RIGHT_ONE, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.LEFT_TWO, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.RIGHT_ONE, MotorType.kBrushless), true);

        setAHRS(new AHRS(Port.kMXP));
        setConstants(Constants.Drive.K_MAX_VELOCITY, Constants.Drive.K_TIMEOUT_MS, Constants.Drive.WHEEL_DIAMETER, Constants.Drive.MIN_BUFFER_COUNT);
        
        // var fpid = Constants.Drive.TURN_FPID;
		// turnController = new PIDController(fpid.kP, fpid.kI, fpid.kD, fpid.kF, ahrs, this, 0.010);
		// turnController.setInputRange(-180.0, 180.0);
		// turnController.setOutputRange(-1.0, 1.0);
		// turnController.setAbsoluteTolerance(Constants.Drive.TOLERANCE_DEGREES);
		// turnController.setContinuous(true);
        // turnController.disable();
        
        // turnEnabledFirstTime = false;

        // spark_pidControllerLeft = leftLeader.getPIDController();
        // spark_pidControllerLeft = rightLeader.getPIDController();
        // leftSparkAnalog = leftOne.getAnalog(AnalogMode.kAbsolute);
        // rightSparkAnalog = rightOne.getAnalog(AnalogMode.kAbsolute);
        // leftSparkEncoder = leftOne.getEncoder();
        // rightSparkEncoder = rightOne.getEncoder();

		configSettings();
    }

    @Override
	public void configPID(int slot) {
        // spark_pidControllerLeft.setP(Constants.Drive.LEFT_PIDS_DOUBLE[0], slot);
        // spark_pidControllerLeft.setI(Constants.Drive.LEFT_PIDS_DOUBLE[1], slot);
        // spark_pidControllerLeft.setD(Constants.Drive.LEFT_PIDS_DOUBLE[2], slot);
        // spark_pidControllerLeft.setFF(Constants.Drive.LEFT_PIDS_DOUBLE[3], slot);

        // spark_pidControllerLeft.setSmartMotionMaxVelocity(Constants.Drive.MAX_VELOCITY, slot);
        // spark_pidControllerLeft.setSmartMotionMaxAccel(Constants.Drive.MAX_ACCELERATION, slot);
        // spark_pidControllerLeft.setSmartMotionMinOutputVelocity(Constants.Drive.MIN_VELOCITY, Constants.Drive.SMART_MOTION_SLOT);
        // spark_pidControllerLeft.setSmartMotionAllowedClosedLoopError(Constants.Drive.ALLOWED_ERR, Constants.Drive.SMART_MOTION_SLOT);

		// spark_pidControllerRight.setP(Constants.Drive.RIGHT_PIDS_DOUBLE[0], slot);
        // spark_pidControllerRight.setI(Constants.Drive.RIGHT_PIDS_DOUBLE[1], slot);
        // spark_pidControllerRight.setD(Constants.Drive.RIGHT_PIDS_DOUBLE[2], slot);
        // spark_pidControllerRight.setFF(Constants.Drive.RIGHT_PIDS_DOUBLE[3], slot);

        // spark_pidControllerRight.setSmartMotionMaxVelocity(Constants.Drive.MAX_VELOCITY, slot);
        // spark_pidControllerRight.setSmartMotionMaxAccel(Constants.Drive.MAX_ACCELERATION, slot);
        // spark_pidControllerRight.setSmartMotionMinOutputVelocity(Constants.Drive.MIN_VELOCITY, Constants.Drive.SMART_MOTION_SLOT);
        // spark_pidControllerRight.setSmartMotionAllowedClosedLoopError(Constants.Drive.ALLOWED_ERR, Constants.Drive.SMART_MOTION_SLOT);
	}

	@Override
	public void configSettings() {
		// var timeoutMs =  Constants.Drive.K_TIMEOUT_MS;

		leftOne.restoreFactoryDefaults();
		leftOne.restoreFactoryDefaults();
		rightOne.restoreFactoryDefaults();
		rightTwo.restoreFactoryDefaults();

		leftOne.setInverted(false);

        leftOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
        leftOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);
        // spark_pidControllerLeft.setOutputRange(-Constants.Drive.MAX_PERCENT_BW, Constants.Drive.MAX_PERCENT_FW);

        leftOne.setSmartCurrentLimit(30, 30);

		leftOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
		

		rightOne.setInverted(true);

        rightOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
        rightOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);
        // spark_pidControllerRight.setOutputRange(-Constants.Drive.MAX_PERCENT_BW, Constants.Drive.MAX_PERCENT_FW);

        rightOne.setSmartCurrentLimit(30, 30);

		rightOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
		
		leftOne.setInverted(false);

        leftOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
        leftOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);
        // spark_pidControllerLeft.setOutputRange(-Constants.Drive.MAX_PERCENT_BW, Constants.Drive.MAX_PERCENT_FW);

        leftOne.setSmartCurrentLimit(30, 30);

		leftOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
		

		rightTwo.setInverted(true);

        rightTwo.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
        rightTwo.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);
        // spark_pidControllerRight.setOutputRange(-Constants.Drive.MAX_PERCENT_BW, Constants.Drive.MAX_PERCENT_FW);

        rightTwo.setSmartCurrentLimit(30, 30);

        rightTwo.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
	}


    public void startRefresh() {
		// SmartDashboard.putNumber("Left P", Constants.Drive.LEFT_PIDS.kP);
		// SmartDashboard.putNumber("Left I", Constants.Drive.LEFT_PIDS.kI);
		// SmartDashboard.putNumber("Left D", Constants.Drive.LEFT_PIDS.kD);
		// SmartDashboard.putNumber("Goto Position L", 0.0);

		// SmartDashboard.putNumber("Right P", Constants.Drive.RIGHT_PIDS.kP);
		// SmartDashboard.putNumber("Right I", Constants.Drive.RIGHT_PIDS.kI);
		// SmartDashboard.putNumber("Right D", Constants.Drive.RIGHT_PIDS.kD);
		// SmartDashboard.putNumber("Goto Position R", 0.0);
    }
    
    public void refreshPID() {
		// spark_pidControllerLeft.setP(SmartDashboard.getNumber("Left P", 0.0), 0);
		// spark_pidControllerLeft.setI(SmartDashboard.getNumber("Left I", 0.0), 0);
		// spark_pidControllerLeft.setD(SmartDashboard.getNumber("Left D", 0.0), 0);
		// spark_pidControllerRight.setP(SmartDashboard.getNumber("Left P", 0.0), 0);
		// spark_pidControllerRight.setI(SmartDashboard.getNumber("Left I", 0.0), 0);
		// spark_pidControllerRight.setD(SmartDashboard.getNumber("Left D", 0.0), 0);

		// leftLeader.set(SmartDashboard.getNumber("Goto Position L", 0.0));
		// rightLeader.set(SmartDashboard.getNumber("Goto Position R", 0.0));
    }
    
    public void enableTurnPID(boolean enabled) {
		// if (enabled) {
		// 	turnEnabledFirstTime = true;
		// }

		// turnController.setEnabled(enabled);
	}

	public void setTurnPID(double setpoint) {
		// turnController.setSetpoint(setpoint);
	}

	// public boolean turnPIDEnabled() {
	// 	// return turnController.isEnabled();
	// }
	
	@Override
	public void pidWrite(double output) {
		// if (turnEnabledFirstTime) {
		// 	vbusArcade(0.3, Constants.Drive.LPF_PERCENT * output);	
		// }
	}

	public void driveForward(double speed) {
		leftOne.set(speed);
		leftTwo.set(speed);
		rightOne.set(speed);
		rightTwo.set(speed);
	}

	public void initDefaultCommand() {
	}
}