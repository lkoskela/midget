Midget
======

_Unit testing Java Portlets outside the container_

Who's it for?
-------------

Midget was developed for the test-infected Java programmer who finds herself writing Java Portlets. There hasn't been many utilities around for writing unit tests for Portlets – most documented approaches seem to rely on deploying a Portlet to a full-blown portal and making requests to the Portlet over HTTP. Midget changes that.

What can I do with it?
----------------------

Midget gives you a couple of utilities for writing tests for a Portlet class. First of all, you get the _MidgetContainer_, which is a lightweight Portlet container that you can instantiate in a setup method and destroy in a teardown method.

    public class MySuperbPortletTest {

        private MidgetContainer container;
        private DeployedPortlet portlet;

        @Before
        public void setUp() throws Exception {
            container = new MidgetContainer();
            portlet = container.deploy(new MySuperbPortlet());
        }

        @After
        public void tearDown() throws Exception {
            container.destroy();
        }
    }

With the the MidgetContainer you can deploy any Portlet you want and Midget gives you a handle called _DeployedPortlet_. With the DeployedPortlet you can manipulate the context in which your Portlet is run and you can tell it to trigger requests to your Portlet. Finally, the DeployedPortlet gives you access to the response – after all, you're most likely to want to assert something about what your Portlet does in the render phase.

Here's an example of what your tests might look like:

    @Test
    public void renderRequestCanProduceContentInEditMode() throws Exception {
        portlet.setPortletMode(EDIT);
        portlet.render();
        assertThat(portlet.renderedContentAsString(),
                containsString("in edit mode"));
    }

Look at the [end-to-end tests](https://github.com/lkoskela/midget/tree/master/src/test/java/com/github/lkoskela/midget/end2end) for more examples of usage.
