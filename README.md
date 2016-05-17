# Spring WS Client: XSD validation and MTOM

Using XSD validating and MTOM simultaneously does not work.

## Reference

* [stackoverflow.com](http://stackoverflow.com/questions/11361689/java-xml-validation-and-mtom)
* [java.net](https://java.net/projects/jax-ws/lists/users/archive/2007-06/message/41)
* [SAP](https://scn.sap.com/thread/3204185)
* [CXF-5237](https://issues.apache.org/jira/browse/CXF-5237)
* [JBOSS](https://issues.jboss.org/browse/JBPAPP-10859)
* [workaround for server](http://forum.spring.io/forum/spring-projects/web-services/31684-mtom-and-xml-validation)

## Log
    Kvě 17, 2016 9:39:35 DOP. org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
    INFO: Loading XML bean definitions from class path resource [spring-ws-context.xml]
    Kvě 17, 2016 9:39:35 DOP. org.springframework.context.support.GenericApplicationContext prepareRefresh
    INFO: Refreshing org.springframework.context.support.GenericApplicationContext@7dc36524: startup date [Tue May 17 09:39:35 CEST 2016]; root of context hierarchy
    Kvě 17, 2016 9:39:35 DOP. org.springframework.ws.soap.saaj.SaajSoapMessageFactory afterPropertiesSet
    INFO: Creating SAAJ 1.3 MessageFactory with SOAP 1.1 Protocol
    Kvě 17, 2016 9:39:36 DOP. org.springframework.oxm.jaxb.Jaxb2Marshaller createJaxbContextFromClasses
    INFO: Creating JAXBContext with classes to be bound [class cz.datalite.spring.mtom.model.File]
    
    org.springframework.oxm.MarshallingFailureException: JAXB marshalling exception; nested exception is javax.xml.bind.MarshalException
     - with linked exception:
    [org.xml.sax.SAXParseException; lineNumber: 0; columnNumber: 0; cvc-type.3.1.2: Element 'ns2:content' is a simple type, so it must have no element information item [children].]
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.convertJaxbException(Jaxb2Marshaller.java:881)
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.marshal(Jaxb2Marshaller.java:666)
        at org.springframework.ws.support.MarshallingUtils.marshal(MarshallingUtils.java:81)
        at org.springframework.ws.client.core.WebServiceTemplate$2.doWithMessage(WebServiceTemplate.java:399)
        at org.springframework.ws.client.core.WebServiceTemplate.doSendAndReceive(WebServiceTemplate.java:590)
        at org.springframework.ws.client.core.WebServiceTemplate.sendAndReceive(WebServiceTemplate.java:555)
        at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:390)
        at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:383)
        at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:373)
        at cz.datalite.spring.mtom.client.WebServiceClient.sendMtom(WebServiceClient.java:24)
        at cz.datalite.spring.mtom.client.WebServiceClientTest.testSimpleSendAndReceive(WebServiceClientTest.java:21)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:74)
        at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:83)
        at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:231)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:88)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
        at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:71)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:174)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
        at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:78)
        at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:212)
        at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:68)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)
    Caused by: javax.xml.bind.MarshalException
     - with linked exception:
    [org.xml.sax.SAXParseException; lineNumber: 0; columnNumber: 0; cvc-type.3.1.2: Element 'ns2:content' is a simple type, so it must have no element information item [children].]
        at com.sun.xml.internal.bind.v2.runtime.MarshallerImpl.write(MarshallerImpl.java:311)
        at com.sun.xml.internal.bind.v2.runtime.MarshallerImpl.marshal(MarshallerImpl.java:236)
        at org.springframework.oxm.jaxb.Jaxb2Marshaller.marshal(Jaxb2Marshaller.java:662)
        ... 40 more
    Caused by: org.xml.sax.SAXParseException; lineNumber: 0; columnNumber: 0; cvc-type.3.1.2: Element 'ns2:content' is a simple type, so it must have no element information item [children].
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:203)
        at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.error(ErrorHandlerWrapper.java:134)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:396)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
        at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:284)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator$XSIErrorReporter.reportError(XMLSchemaValidator.java:452)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.reportSchemaError(XMLSchemaValidator.java:3230)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.elementLocallyValidType(XMLSchemaValidator.java:3135)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.processElementContent(XMLSchemaValidator.java:3055)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.handleEndElement(XMLSchemaValidator.java:2134)
        at com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.endElement(XMLSchemaValidator.java:853)
        at com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHandlerImpl.endElement(ValidatorHandlerImpl.java:584)
        at org.xml.sax.helpers.XMLFilterImpl.endElement(XMLFilterImpl.java:570)
        at com.sun.xml.internal.bind.v2.runtime.output.SAXOutput.endTag(SAXOutput.java:117)
        at com.sun.xml.internal.bind.v2.runtime.output.XmlOutputAbstractImpl.endTag(XmlOutputAbstractImpl.java:109)
        at com.sun.xml.internal.bind.v2.runtime.output.ForkXmlOutput.endTag(ForkXmlOutput.java:76)
        at com.sun.xml.internal.bind.v2.runtime.output.MTOMXmlOutput.endTag(MTOMXmlOutput.java:94)
        at com.sun.xml.internal.bind.v2.runtime.XMLSerializer.leafElement(XMLSerializer.java:342)
        at com.sun.xml.internal.bind.v2.model.impl.RuntimeBuiltinLeafInfoImpl$PcdataImpl.writeLeafElement(RuntimeBuiltinLeafInfoImpl.java:171)
        at com.sun.xml.internal.bind.v2.runtime.reflect.TransducedAccessor$CompositeTransducedAccessorImpl.writeLeafElement(TransducedAccessor.java:239)
        at com.sun.xml.internal.bind.v2.runtime.property.SingleElementLeafProperty.serializeBody(SingleElementLeafProperty.java:115)
        at com.sun.xml.internal.bind.v2.runtime.ClassBeanInfoImpl.serializeBody(ClassBeanInfoImpl.java:345)
        at com.sun.xml.internal.bind.v2.runtime.XMLSerializer.childAsSoleContent(XMLSerializer.java:578)
        at com.sun.xml.internal.bind.v2.runtime.ClassBeanInfoImpl.serializeRoot(ClassBeanInfoImpl.java:326)
        at com.sun.xml.internal.bind.v2.runtime.XMLSerializer.childAsRoot(XMLSerializer.java:479)
        at com.sun.xml.internal.bind.v2.runtime.MarshallerImpl.write(MarshallerImpl.java:308)
        ... 42 more
    
    
    Process finished with exit code -1
