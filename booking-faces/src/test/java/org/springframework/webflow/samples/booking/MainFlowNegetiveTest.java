package org.springframework.webflow.samples.booking;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;

import org.easymock.EasyMock;
import org.springframework.binding.mapping.Mapper;
import org.springframework.binding.mapping.MappingResults;
import org.springframework.faces.model.converter.FacesConversionService;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.engine.EndState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

public class MainFlowNegetiveTest extends AbstractXmlFlowExecutionTests {

    private BookingService bookingService;

    protected void setUp() {
	bookingService = EasyMock.createMock(BookingService.class);
    }

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
	return resourceFactory.createFileResource("src/main/webapp/WEB-INF/flows/main/main-flow.xml");
    }

    @Override
    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
	builderContext.registerBean("bookingService", bookingService);
	builderContext.getFlowBuilderServices().setConversionService(new FacesConversionService());
    }

	
    public void testLoginMainFlow() {
	List<Booking> bookings = new ArrayList<Booking>();
	bookings.add(new Booking(new Hotel(), new User("keiths", "password", "Keith Donald")));
	EasyMock.expect(bookingService.findBookings("keiths")).andReturn(bookings);
	EasyMock.replay(bookingService);


    }

}
