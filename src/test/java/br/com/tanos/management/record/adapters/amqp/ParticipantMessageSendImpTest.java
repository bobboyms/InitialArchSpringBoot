package br.com.tanos.management.record.adapters.amqp;

import br.com.tanos.management.record.ports.dto.ParticipantDto;
import org.junit.ClassRule;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.Callable;

//import static org.junit.Assert.*;



@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ParticipantMessageSendImpTest.Initializer.class)
@Testcontainers
class ParticipantMessageSendImpTest {

    @Container
//    @ClassRule
    private static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3-management").
            withExposedPorts(5672, 15672).
            withUser("admin","admin");

//    @ClassRule
//    public static GenericContainer rabbit = new GenericContainer("rabbitmq:3-management")
//            .withExposedPorts(5672, 15672);
//    @Rule
//    public OutputCaptureRule outputCapture = new OutputCaptureRule();

    @Autowired
    private ParticipantMessageSendImp messageSender;

    @Test
    public void testBroadcast() {
        assertTrue(rabbitMQContainer.isRunning());
        System.out.println(rabbitMQContainer.getAdminUsername());
        System.out.println(rabbitMQContainer.getMappedPort(5672));
        messageSender.sendMessage(new ParticipantDto("Thiago","88822233378"));
//        await().atMost(5, TimeUnit.SECONDS).until(isMessageConsumed(), is(true));
    }
//    private Callable<Boolean> isMessageConsumed() {
//        return () -> outputCapture.toString().contains("Broadcast Test");
//    }
//
    public static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            var values = TestPropertyValues.of(
                    "spring.rabbitmq.host=" + rabbitMQContainer.getContainerIpAddress(),
                    "spring.rabbitmq.port=" + rabbitMQContainer.getMappedPort(5672)
            );
            values.applyTo(configurableApplicationContext);
        }
    }

//    @Test
//    public void sendMessage() {
//
//    }
}