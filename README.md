# Spring WS Client: XSD validation and MTOM

Using XSD validating and MTOM simultaneously does not work.

## Bug report
* [SWS-958: Soap MTOM: mtomEnabled=true and XSD schemas=... fails](https://jira.spring.io/browse/SWS-958)
* [SPM-414: internal project bug report](http://jira.datalite.cz/browse/SPM-414) (no public access)

## References

* [stackoverflow.com](http://stackoverflow.com/questions/11361689/java-xml-validation-and-mtom)
* [java.net](https://java.net/projects/jax-ws/lists/users/archive/2007-06/message/41)
* [SAP](https://scn.sap.com/thread/3204185)
* [CXF-5237](https://issues.apache.org/jira/browse/CXF-5237)
* [JBOSS](https://issues.jboss.org/browse/JBPAPP-10859)
* [workaround for server](http://forum.spring.io/forum/spring-projects/web-services/31684-mtom-and-xml-validation)

## Log
```
org.springframework.oxm.MarshallingFailureException: JAXB marshalling exception

	at org.springframework.oxm.jaxb.Jaxb2Marshaller.convertJaxbException(Jaxb2Marshaller.java:951)
	at org.springframework.oxm.jaxb.Jaxb2Marshaller.marshal(Jaxb2Marshaller.java:716)
	at org.springframework.ws.support.MarshallingUtils.marshal(MarshallingUtils.java:80)
	at org.springframework.ws.client.core.WebServiceTemplate$2.doWithMessage(WebServiceTemplate.java:399)
	at org.springframework.ws.client.core.WebServiceTemplate.doSendAndReceive(WebServiceTemplate.java:569)
	at org.springframework.ws.client.core.WebServiceTemplate.sendAndReceive(WebServiceTemplate.java:539)
	at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:391)
	at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:385)
	at org.springframework.ws.client.core.WebServiceTemplate.marshalSendAndReceive(WebServiceTemplate.java:375)
	at cz.datalite.spring.mtom.client.WebServiceClient.sendMarshal(WebServiceClient.java:42)
	at cz.datalite.spring.mtom.client.WebServiceClientTest.testMarshallMtom(WebServiceClientTest.java:55)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.springframework.test.context.junit4.statements.RunBeforeTestExecutionCallbacks.evaluate(RunBeforeTestExecutionCallbacks.java:76)
	at org.springframework.test.context.junit4.statements.RunAfterTestExecutionCallbacks.evaluate(RunAfterTestExecutionCallbacks.java:84)
	at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)
	at org.springframework.test.context.junit4.statements.RunBeforeTestMethodCallbacks.evaluate(RunBeforeTestMethodCallbacks.java:75)
	at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:27)
	at org.springframework.test.context.junit4.statements.RunAfterTestMethodCallbacks.evaluate(RunAfterTestMethodCallbacks.java:86)
	at org.springframework.test.context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:84)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:252)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:97)
	at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
	at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
	at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:69)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:232)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:55)
Caused by: jakarta.xml.bind.MarshalException
 - with linked exception:
[org.xml.sax.SAXParseException; lineNumber: 0; columnNumber: 0; cvc-type.3.1.2: Element 'ns2:content' is a simple type, so it must have no element information item [children].]
	at org.glassfish.jaxb.runtime.v2.runtime.MarshallerImpl.write(MarshallerImpl.java:268)
	at org.glassfish.jaxb.runtime.v2.runtime.MarshallerImpl.marshal(MarshallerImpl.java:197)
	at org.springframework.oxm.jaxb.Jaxb2Marshaller.marshal(Jaxb2Marshaller.java:712)
	... 44 more
Caused by: org.xml.sax.SAXParseException; lineNumber: 0; columnNumber: 0; cvc-type.3.1.2: Element 'ns2:content' is a simple type, so it must have no element information item [children].
	at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:204)
	at java.xml/com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.error(ErrorHandlerWrapper.java:135)
	at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:396)
	at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:327)
	at java.xml/com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:284)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator$XSIErrorReporter.reportError(XMLSchemaValidator.java:512)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.reportSchemaError(XMLSchemaValidator.java:3600)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.elementLocallyValidType(XMLSchemaValidator.java:3427)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.processElementContent(XMLSchemaValidator.java:3347)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.handleEndElement(XMLSchemaValidator.java:2373)
	at java.xml/com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaValidator.endElement(XMLSchemaValidator.java:944)
	at java.xml/com.sun.org.apache.xerces.internal.jaxp.validation.ValidatorHandlerImpl.endElement(ValidatorHandlerImpl.java:585)
	at java.xml/org.xml.sax.helpers.XMLFilterImpl.endElement(XMLFilterImpl.java:558)
	at org.glassfish.jaxb.runtime.v2.runtime.output.SAXOutput.endTag(SAXOutput.java:105)
	at org.glassfish.jaxb.runtime.v2.runtime.output.XmlOutputAbstractImpl.endTag(XmlOutputAbstractImpl.java:105)
	at org.glassfish.jaxb.runtime.v2.runtime.output.ForkXmlOutput.endTag(ForkXmlOutput.java:59)
	at org.glassfish.jaxb.runtime.v2.runtime.output.MTOMXmlOutput.endTag(MTOMXmlOutput.java:85)
	at org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer.leafElement(XMLSerializer.java:321)
	at org.glassfish.jaxb.runtime.v2.model.impl.RuntimeBuiltinLeafInfoImpl$PcdataImpl.writeLeafElement(RuntimeBuiltinLeafInfoImpl.java:151)
	at org.glassfish.jaxb.runtime.v2.runtime.reflect.TransducedAccessor$CompositeTransducedAccessorImpl.writeLeafElement(TransducedAccessor.java:224)
	at org.glassfish.jaxb.runtime.v2.runtime.property.SingleElementLeafProperty.serializeBody(SingleElementLeafProperty.java:94)
	at org.glassfish.jaxb.runtime.v2.runtime.ClassBeanInfoImpl.serializeBody(ClassBeanInfoImpl.java:334)
	at org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer.childAsSoleContent(XMLSerializer.java:556)
	at org.glassfish.jaxb.runtime.v2.runtime.ClassBeanInfoImpl.serializeRoot(ClassBeanInfoImpl.java:314)
	at org.glassfish.jaxb.runtime.v2.runtime.XMLSerializer.childAsRoot(XMLSerializer.java:456)
	at org.glassfish.jaxb.runtime.v2.runtime.MarshallerImpl.write(MarshallerImpl.java:265)
	... 46 more
```