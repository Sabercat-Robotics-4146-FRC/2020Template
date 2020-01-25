package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {
  private static final int kJoystickPort = 0;
  private static final int rightLeadDeviceID = 2;
  private static final int rightFollowDeviceID = 6;
  private static final int leftLeadDeviceID = 3;
  private static final int leftFollowDeviceID = 4;

  private CANSparkMax rightLeadMotor;
  private CANSparkMax rightFollowMotor;
  private CANSparkMax leftLeadMotor;
  private CANSparkMax leftFollowMotor;

  private DifferentialDrive robotDrive;

  private Joystick joystick;

  @Override
  public void robotInit() {
    rightLeadMotor = new CANSparkMax(rightLeadDeviceID, MotorType.kBrushless);
    rightFollowMotor = new CANSparkMax(rightFollowDeviceID, MotorType.kBrushless);
    leftLeadMotor = new CANSparkMax(leftLeadDeviceID, MotorType.kBrushless);
    leftFollowMotor = new CANSparkMax(leftFollowDeviceID, MotorType.kBrushless);

    rightLeadMotor.restoreFactoryDefaults();
    rightFollowMotor.restoreFactoryDefaults();
    leftLeadMotor.restoreFactoryDefaults();
    leftFollowMotor.restoreFactoryDefaults();

    rightFollowMotor.follow(rightLeadMotor); 
    leftFollowMotor.follow(leftLeadMotor);

    robotDrive = new DifferentialDrive(leftLeadMotor, rightLeadMotor);

    joystick = new Joystick(kJoystickPort);
  }

  @Override
  public void teleopPeriodic() {

    robotDrive.arcadeDrive(-joystick.getY(), joystick.getX());
  }
}