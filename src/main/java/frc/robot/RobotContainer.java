package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.RomiDrivetrain;

public class RobotContainer 
{
    private final RomiDrivetrain romiDrivetrain = new RomiDrivetrain();
    private final CommandXboxController xbox = new CommandXboxController(0);

    RobotContainer()
    {}

    public RomiDrivetrain getRomiDrivetrain()
    {
        return romiDrivetrain;
    }

    public CommandXboxController getXbox()
    {
        return xbox;
    }
}
