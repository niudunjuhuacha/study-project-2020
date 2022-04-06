/**
 * CSPRequestServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.iptv.bokong.soap;

public class CSPRequestServiceTestCase extends junit.framework.TestCase {
    public CSPRequestServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testctmsWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new CSPRequestServiceLocator().getctmsAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new CSPRequestServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1ctmsExecCmd() throws Exception {
        CtmsSoapBindingStub binding;
        try {
            binding = (CtmsSoapBindingStub)
                          new CSPRequestServiceLocator().getctms();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null) {
                jre.getLinkedCause().printStackTrace();
            }
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        CSPResult value = null;
        value = binding.execCmd(new java.lang.String(), new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

}
