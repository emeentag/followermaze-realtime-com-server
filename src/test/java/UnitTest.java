
/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import unit.EventConsumerManagerTest;
import unit.EventConsumerTest;
import unit.EventReceiverHandlerTest;
import unit.UserRegisterHandlerTest;

// @Suite.SuiteClasses({ UserRegisterHandlerTest.class, EventReceiverHandlerTest.class, EventConsumerManagerTest.class,
//     EventConsumerTest.class })

@RunWith(Suite.class)
@Suite.SuiteClasses({ UserRegisterHandlerTest.class, EventReceiverHandlerTest.class, EventConsumerManagerTest.class,
    EventConsumerTest.class })
public class UnitTest {

}
