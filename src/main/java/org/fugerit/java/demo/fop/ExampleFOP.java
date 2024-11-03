package org.fugerit.java.demo.fop;

import org.apache.fop.apps.*;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class ExampleFOP {

    private ExampleFOP() {}

    /*
     * based on :
     * https://xmlgraphics.apache.org/fop/trunk/embedding.html
     */
    public static void writePdfExample(OutputStream out) throws SAXException, TransformerException, IOException, URISyntaxException {
        try (InputStream confStream = Thread.currentThread().getContextClassLoader().getResourceAsStream( "fop/fop.xconf" );
             InputStream foStream = Thread.currentThread().getContextClassLoader().getResourceAsStream( "fop/hello.fo" ) )  {
            FopConfParser confParser =  new FopConfParser( confStream, new URI(".") );
            FopFactoryBuilder confBuilder = confParser.getFopFactoryBuilder();
            FopFactory fopFactory = confBuilder.build();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Source src = new StreamSource(foStream);
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);
        }
    }

}
