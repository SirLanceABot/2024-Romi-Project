package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RomiLED extends SubsystemBase
{
    private final DigitalOutput led;
    private final Timer timer = new Timer();

    public RomiLED(int port)
    {
        led = new DigitalOutput(port);
        timer.reset();
        timer.start();
    }

    public void on()
    {
        led.set(true);
    }

    public void off()
    {
        led.set(false);
    }

    public Command onCommand()
    {
        return runOnce( this::on );
    }

    public Command offCommand()
    {
        return runOnce( this::off );
    }

    private void blink()
    {
        double currentTime = timer.get();
        double decimalPart = Math.abs( currentTime - (int) currentTime );
        if(decimalPart < 0.5)
            on();
        else
            off();
    }
    
    public Command blinkCommand()
    {
        return runEnd( this::blink, this::off );
    }    
}
