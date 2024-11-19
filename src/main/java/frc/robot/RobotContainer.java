package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.sensors.Bumper;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.RomiLED;

public class RobotContainer 
{
    private final RomiDrivetrain romiDrivetrain = new RomiDrivetrain();
    private final RomiLED greenLED = new RomiLED(1);
    private final RomiLED redLED = new RomiLED(2);
    private final Bumper frontBumper = new Bumper(8);

    private final CommandXboxController xbox = new CommandXboxController(0);

    RobotContainer()
    {}

    public RomiDrivetrain getRomiDrivetrain()
    {
        return romiDrivetrain;
    }

    public RomiLED getGreenLED()
    {
        return greenLED;
    }

    public RomiLED getRedLED()
    {
        return redLED;
    }

    public Bumper getFrontBumper()
    {
        return frontBumper;
    }

    public CommandXboxController getXbox()
    {
        return xbox;
    }
}
