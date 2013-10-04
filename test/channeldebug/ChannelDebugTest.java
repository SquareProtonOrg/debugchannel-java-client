package channeldebug;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.LinkedList;

import static junit.framework.Assert.*;

public class ChannelDebugTest extends TestCase {

    private ChannelDebug debug;

    @Override
    public void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
        debug = new ChannelDebug("http://192.168.2.17", 1025, "hello/world");
    }

    public void testLogOfNullDoesNotThrowException()
    {
        debug.log(null);
    }

    public void testLogOfStringDoesNotThrowException()
    {
        debug.log("hello");
    }

    public void testLogOfIntDoesNotThrowException()
    {
        debug.log(3);
    }

    public void testLogOfAnonymousClassDoesNotThrowException()
    {
        debug.log(new Runnable() {
            @Override
            public void run() {
                // do something
            }
        });
    }

    public void testLogOfObjectDoesNotThrowException()
    {
        debug.log(new Object());
    }

    @Test(timeout=1000)
    public void testLogOfGraphDoesNotCauseStackOverflow()
    {
        Node n1 = new Node("n1");
        Node n2 = new Node("n2");

        n1.setNode(n2);
        n2.setNode(n1);

        debug.log(n1);
    }

}


class Node
{
    private String name;
    private Node node;

    public Node(String name)
    {
        this.name = name;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

}