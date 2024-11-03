package org.fugerit.java.demo;

import org.fugerit.java.demo.fop.ExampleFOP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

class StandaloneFOPTest {

    private static Logger logger = LoggerFactory.getLogger( StandaloneFOPTest.class );

    @Test
    void test() {
        logger.info( "START TEST" );
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ExampleFOP.writePdfExample( out );
            Assertions.assertTrue( out.toByteArray().length > 0 );
        } catch (Exception e) {
            String message = String.format( "ERROR : %s", e );
            logger.error( message, e );
            Assertions.fail( message );
        }
        logger.info( "END TEST" );
    }

}
