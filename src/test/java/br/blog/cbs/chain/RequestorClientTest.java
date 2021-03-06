package br.blog.cbs.chain;

import br.blog.cbs.Application;
import br.blog.cbs.chain.handler.AbstractSupportHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

import static br.blog.cbs.chain.handler.AbstractSupportHandler.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class RequestorClientTest {

    @Autowired
    RequestorClient requestorClient;

    @Test
    public void testGetHandlerChainLevelOne() {
        List<String> actions = new ArrayList<>();
        AbstractSupportHandler handler = requestorClient.getHandlerChain();
        handler.receiveRequest(TECHNICAL, actions);
        Assert.assertEquals(actions.size(), 1);
        Assert.assertEquals(actions.get(0), "TechnicalSupportHandler");
    }

    @Test
    public void testGetHandlerChainLevelTwo() {
        List<String> actions = new ArrayList<>();
        AbstractSupportHandler handler = requestorClient.getHandlerChain();
        handler.receiveRequest(BILLING, actions);
        Assert.assertEquals(actions.size(), 2);
        Assert.assertEquals(actions.get(0), "TechnicalSupportHandler");
        Assert.assertEquals(actions.get(1), "BillingSupportHandler");
    }

    @Test
    public void testGetHandlerChainLevelThree() {
        List<String> actions = new ArrayList<>();
        AbstractSupportHandler handler = requestorClient.getHandlerChain();
        handler.receiveRequest(GENERAL, actions);
        Assert.assertEquals(actions.size(), 3);
        Assert.assertEquals(actions.get(0), "TechnicalSupportHandler");
        Assert.assertEquals(actions.get(1), "BillingSupportHandler");
        Assert.assertEquals(actions.get(2), "GeneralSupportHandler");
    }

}
