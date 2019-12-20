package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.frc5587.lib.pathfinder.SparkAbstractDrive;

import edu.wpi.first.wpilibj.PIDOutput;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drive extends SparkAbstractDrive implements PIDOutput {

	public Drive() {
		super(new CANSparkMax(RobotMap.Drive.LEFT_ONE, MotorType.kBrushless),
				new CANSparkMax(RobotMap.Drive.RIGHT_ONE, MotorType.kBrushless),
				new CANSparkMax(RobotMap.Drive.LEFT_TWO, MotorType.kBrushless),
				new CANSparkMax(RobotMap.Drive.RIGHT_ONE, MotorType.kBrushless), true);

		setConstants(Constants.Drive.K_MAX_VELOCITY, Constants.Drive.K_TIMEOUT_MS, Constants.Drive.WHEEL_DIAMETER,
				Constants.Drive.MIN_BUFFER_COUNT);

		configSettings();
	}

	@Override
	public void configPID(int slot) {

	}

	@Override
	public void configSettings() {

		leftOne.restoreFactoryDefaults();
		leftOne.restoreFactoryDefaults();
		rightOne.restoreFactoryDefaults();
		rightTwo.restoreFactoryDefaults();

		leftOne.setInverted(false);

		leftOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
		leftOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);

		leftOne.setSmartCurrentLimit(30, 30);

		leftOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);

		rightOne.setInverted(true);

		rightOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
		rightOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);

		rightOne.setSmartCurrentLimit(30, 30);

		rightOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);

		leftOne.setInverted(false);

		leftOne.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
		leftOne.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);

		leftOne.setSmartCurrentLimit(30, 30);

		leftOne.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);

		rightTwo.setInverted(true);

		rightTwo.setSoftLimit(SoftLimitDirection.kForward, Constants.Drive.MAX_PERCENT_FW);
		rightTwo.setSoftLimit(SoftLimitDirection.kReverse, -Constants.Drive.MAX_PERCENT_BW);

		rightTwo.setSmartCurrentLimit(30, 30);

		rightTwo.enableVoltageCompensation(Constants.Drive.V_COMP_SATURATION);
	}

	public void refreshPID() {

	}

	public void enableTurnPID(boolean enabled) {

	}

	public void setTurnPID(double setpoint) {

	}

	@Override
	public void pidWrite(double output) {

	}

	public void driveForward(double speed) {
		leftOne.set(-speed);
		leftTwo.set(-speed);
		rightOne.set(-speed);
		rightTwo.set(-speed);
	}

	public void initDefaultCommand() {

	}
}