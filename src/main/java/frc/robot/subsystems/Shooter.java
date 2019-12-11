/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax shooterMax;
  private TalonSRX deJammer;

  public Shooter() {
    shooterMax = new CANSparkMax(RobotMap.Shooter.SHOOT_SPARKMAX, MotorType.kBrushless);
    shooterMax.setSmartCurrentLimit(40, 40);
    deJammer = new TalonSRX(RobotMap.Shooter.SHOOT_TALON);
  }

  public void shoot() {
    shooterMax.set(1);
  }

  public void backwardsShoot() {
    shooterMax.set(-1);
  }

  public void bestShoot(double val) {
    shooterMax.set(val);
  }
  public void unJam(){
    deJammer.set(ControlMode.PercentOutput, -1);
  }
  public void jamDisabled(){
    deJammer.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}