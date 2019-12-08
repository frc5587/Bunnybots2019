package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANAnalog.AnalogMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.frc5587.lib.LimitedHashMap;
import org.frc5587.lib.pathfinder.SparkAbstractDrive;
import org.frc5587.lib.pathfinder.Pathgen;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drive extends SparkAbstractDrive implements PIDOutput {

    public static final Pathgen SLOW_PATHGEN = new Pathgen(30, 0.010, 36, 60, 120);
	public static final Pathgen MED_PATHGEN = new Pathgen(30, 0.010, 60, 80, 160);
    public static final Pathgen FAST_PATHGEN = new Pathgen(30, 0.010, 84, 80, 160);
  
    private LimitedHashMap<Double, Double> gyroHistory;
	private ArrayList<Double> visionTimeDeltas;
	private Double visionTimeDelta;
    private PIDController turnController;
    private boolean turnEnabledFirstTime;
    
    public Drive() {
        super(new CANSparkMax(RobotMap.Drive.LEFT_LEADER, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.RIGHT_LEADER, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.LEFT_FOLLOWER, MotorType.kBrushless),
                new CANSparkMax(RobotMap.Drive.RIGHT_FOLLOWER, MotorType.kBrushless), true);

        setAHRS(new AHRS(Port.kMXP));
        setConstants(Constants.Drive.K_MAX_VELOCITY, Constants.Drive.K_TIMEOUT_MS, Constants.Drive.WHEEL_DIAMETER, Constants.Drive.MIN_BUFFER_COUNT);
        
        gyroHistory = new LimitedHashMap<>(Constants.Drive.GYRO_HISTORY_LENGTH);
        visionTimeDeltas = new ArrayList<>();
        
        var fpid = Constants.Drive.TURN_FPID;
		turnController = new PIDController(fpid.kP, fpid.kI, fpid.kD, fpid.kF, ahrs, this, 0.010);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(Constants.Drive.TOLERANCE_DEGREES);
		turnController.setContinuous(true);
        turnController.disable();
        
        turnEnabledFirstTime = false;

        spark_pidControllerLeft = leftLeader.getPIDController();
        spark_pidControllerLeft = rightLeader.getPIDController();
        leftSparkAnalog = leftLeader.getAnalog(AnalogMode.kAbsolute);
        rightSparkAnalog = rightLeader.getAnalog(AnalogMode.kAbsolute);
        leftSparkEncoder = leftLeader.getEncoder();
        rightSparkEncoder = rightLeader.getEncoder();

        configureSpark();
    }

    private void configureSpark() {
        leftLeader.setInverted(false);

        leftLeader.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
        leftLeader.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);
        spark_pidControllerLeft.setOutputRange(-Constants.Drive.MAX_PERCENT_BW, Constants.Drive.MAX_PERCENT_FW);

        leftLeader.setSmartCurrentLimit(40, 35);

        leftLeader.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
    }

    @Override
	public void configPID(int slot) {
        spark_pidControllerLeft.setP(Constants.Drive.LEFT_PIDS_DOUBLE[0], slot);
        spark_pidControllerLeft.setI(Constants.Drive.LEFT_PIDS_DOUBLE[1], slot);
        spark_pidControllerLeft.setD(Constants.Drive.LEFT_PIDS_DOUBLE[2], slot);
        spark_pidControllerLeft.setFF(Constants.Drive.LEFT_PIDS_DOUBLE[3], slot);

        // spark_pidControllerLeft.setSmartMotionMaxVelocity(Constants.Drive.MAX_VELOCITY, Constants.Drive.K_TIMEOUT_MS);
        spark_pidControllerLeft.setSmartMotionMaxVelocity(Constants.Drive.MAX_VELOCITY, slot);
        spark_pidControllerLeft.setSmartMotionMaxAccel(Constants.Drive.MAX_ACCELERATION, slot);
        // spark_pidControllerLeft.setSmartMotionMinOutputVelocity(Constants.Drive.MIN_VELOCITY, Constants.Drive.SMART_MOTION_SLOT);
        spark_pidControllerLeft.setSmartMotionMinOutputVelocity(Constants.Drive.MIN_VELOCITY, Constants.Drive.SMART_MOTION_SLOT);
        spark_pidControllerLeft.setSmartMotionAllowedClosedLoopError(Constants.Drive.ALLOWED_ERR, Constants.Drive.SMART_MOTION_SLOT);

		spark_pidControllerRight.setP(Constants.Drive.RIGHT_PIDS_DOUBLE[0], slot);
        spark_pidControllerRight.setI(Constants.Drive.RIGHT_PIDS_DOUBLE[1], slot);
        spark_pidControllerRight.setD(Constants.Drive.RIGHT_PIDS_DOUBLE[2], slot);
        spark_pidControllerRight.setFF(Constants.Drive.RIGHT_PIDS_DOUBLE[3], slot);

        spark_pidControllerRight.setSmartMotionMaxVelocity(Constants.Drive.MAX_VELOCITY, slot);
        spark_pidControllerRight.setSmartMotionMaxAccel(Constants.Drive.MAX_ACCELERATION, slot);
        spark_pidControllerRight.setSmartMotionMinOutputVelocity(Constants.Drive.MIN_VELOCITY, Constants.Drive.SMART_MOTION_SLOT);
        spark_pidControllerRight.setSmartMotionAllowedClosedLoopError(Constants.Drive.ALLOWED_ERR, Constants.Drive.SMART_MOTION_SLOT);
	}

	@Override
	public void configSettings() {
		var timeoutMs =  Constants.Drive.K_TIMEOUT_MS;
		// rightMaster.configVoltageCompSaturation(Constants.K_V_COMP_SATURATION, timeoutMs);
		// rightMaster.enableVoltageCompensation(true);
		// leftMaster.configVoltageCompSaturation(Constants.K_V_COMP_SATURATION, timeoutMs);
		// leftMaster.enableVoltageCompensation(true);

		// rightMaster.configPeakCurrentLimit(35, timeoutMs);
		// leftMaster.configPeakCurrentLimit(35, timeoutMs);
		// rightMaster.configPeakCurrentDuration(20, timeoutMs);
		// leftMaster.configPeakCurrentDuration(20, timeoutMs);
		// rightMaster.configContinuousCurrentLimit(35, timeoutMs);
		// leftMaster.configContinuousCurrentLimit(35, timeoutMs);
		// rightMaster.enableCurrentLimit(true);
		// leftMaster.enableCurrentLimit(true);

		// leftMaster.configPeakOutputForward(Constants.Drive.MAX_PERCENT_FW, timeoutMs);
		// leftMaster.configPeakOutputReverse(-Constants.Drive.MAX_PERCENT_BW, timeoutMs);
		// rightMaster.configPeakOutputForward(Constants.Drive.MAX_PERCENT_FW, timeoutMs);
		// rightMaster.configPeakOutputReverse(-Constants.Drive.MAX_PERCENT_BW, timeoutMs);
	}


    public void startRefresh() {
		SmartDashboard.putNumber("Left P", Constants.Drive.LEFT_PIDS.kP);
		SmartDashboard.putNumber("Left I", Constants.Drive.LEFT_PIDS.kI);
		SmartDashboard.putNumber("Left D", Constants.Drive.LEFT_PIDS.kD);
		SmartDashboard.putNumber("Goto Position L", 0.0);

		SmartDashboard.putNumber("Right P", Constants.Drive.RIGHT_PIDS.kP);
		SmartDashboard.putNumber("Right I", Constants.Drive.RIGHT_PIDS.kI);
		SmartDashboard.putNumber("Right D", Constants.Drive.RIGHT_PIDS.kD);
		SmartDashboard.putNumber("Goto Position R", 0.0);
    }
    
    public void refreshPID() {
		spark_pidControllerLeft.setP(SmartDashboard.getNumber("Left P", 0.0), 0);
		spark_pidControllerLeft.setI(SmartDashboard.getNumber("Left I", 0.0), 0);
		spark_pidControllerLeft.setD(SmartDashboard.getNumber("Left D", 0.0), 0);
		spark_pidControllerRight.setP(SmartDashboard.getNumber("Left P", 0.0), 0);
		spark_pidControllerRight.setI(SmartDashboard.getNumber("Left I", 0.0), 0);
		spark_pidControllerRight.setD(SmartDashboard.getNumber("Left D", 0.0), 0);

		leftLeader.set(SmartDashboard.getNumber("Goto Position L", 0.0));
		rightLeader.set(SmartDashboard.getNumber("Goto Position R", 0.0));
    }
    
    public void enableTurnPID(boolean enabled) {
		if (enabled) {
			turnEnabledFirstTime = true;
		}

		turnController.setEnabled(enabled);
	}

	public void setTurnPID(double setpoint) {
		turnController.setSetpoint(setpoint);
	}

	public boolean turnPIDEnabled() {
		return turnController.isEnabled();
	}

	public void setVisionTimeDelta(double visionTimeDelta) {
		visionTimeDeltas.add(visionTimeDelta);

		double sum = 0;
		for (var delta : visionTimeDeltas) {
			sum += delta;
		}

		this.visionTimeDelta = sum / visionTimeDeltas.size();
	}
	
	public void updateGyroHistory() {
		if (visionTimeDelta != null) {
			gyroHistory.put(Timer.getFPGATimestamp() + visionTimeDelta, getHeading(180.0));
		}
	}

	public double getAngleAtClosestTime(double time) {
		double lastVal = Double.NaN;
		double lastDeltaSign = Double.NaN;

		time += visionTimeDelta;

		var currentCopy = new LinkedHashMap<>(gyroHistory);

		// gyroHistory array is sorted, given that it's made up of times
		for (var entry : currentCopy.keySet()) {
			// When sign of delta changes, we know we have overshot, so use last (closest) value
			var delta = time - entry;
			var sign = Math.signum(delta);
			if (lastDeltaSign != Double.NaN && lastDeltaSign != sign) {
				break;
			} else {
				lastVal = entry;
				lastDeltaSign = sign;
			}
		}

		System.out.println("TARGET TIME: " + time);
		System.out.println("LAST VAL: " + lastVal);

		return gyroHistory.get(lastVal);
	}

	@Override
	public void pidWrite(double output) {
		if (turnEnabledFirstTime) {
			vbusArcade(0.3, Constants.Drive.LPF_PERCENT * output);	
		}
	}

	public void initDefaultCommand() {
	}
}