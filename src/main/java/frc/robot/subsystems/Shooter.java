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

public class Shooter extends Subsystem {
  private CANSparkMax shooterMax;
  private TalonSRX deJammer;

  public Shooter() {
    shooterMax = new CANSparkMax(RobotMap.Shooter.SHOOT_SPARKMAX, MotorType.kBrushless);
    shooterMax.setSmartCurrentLimit(40, 40);
    deJammer = new TalonSRX(RobotMap.Shooter.SHOOT_TALON);
  }

  public void setShooterSpeed(double val) {
    shooterMax.set(val);
  }

  public void setDeJammerSpeed(double percent) {
    deJammer.set(ControlMode.PercentOutput, percent);
  }

  @Override
  public void initDefaultCommand() {
  
  }
}