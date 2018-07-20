
package test.retrospector.core.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Presenter;
import retrospector.core.boundry.Request;
import retrospector.core.datagateway.DataGateway;
import retrospector.core.entity.Factoid;
import retrospector.core.interactor.CrudFactoidRequest;
import retrospector.core.interactor.CrudFactoidUseCase;
import retrospector.core.interactor.CrudRequest.Crud;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;
import test.retrospector.core.utility.TestEntity;

@RunWith(MockitoJUnitRunner.class)
public class CrudFactoidUseCaseTest {
    
    @Mock
    private DataGateway dataGateway;
    @Mock
    private Presenter presenter;
    private Interactor useCase;
    
    private Factoid factoid;
    private RequestableFactoid requestableFactoid;
    
    @Before
    public void setUp() {
        useCase = new CrudFactoidUseCase(dataGateway, presenter);
        factoid = TestEntity.getFactoid();
        requestableFactoid = EntityConverter.convert(factoid);
        
        when(dataGateway.addFactoid(factoid)).thenAnswer(i -> factoid);
        when(dataGateway.getFactoid(factoid.getId())).thenAnswer(i -> factoid);
        when(dataGateway.updateFactoid(factoid)).thenAnswer(i -> factoid);
    }
    
    @Test
    public void addFactoid_CallsDataGateway() {
        Request request = new CrudFactoidRequest(CrudFactoidRequest.Crud.Create, requestableFactoid);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).addFactoid(factoid);
    }
        
    @Test
    public void addFactoid_CallsPresenter() {
        Request request = new CrudFactoidRequest(Crud.Create, requestableFactoid);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).factoidAdded(requestableFactoid);
    }
    
    @Test
    public void readFactoid_CallsDataGateway() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Read, factoidId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).getFactoid(factoidId);
    }
    
    @Test
    public void readFactoid_CallsPresenter() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Read, factoidId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).factoidRetrieved(requestableFactoid);
    }
    
    @Test
    public void updateFactoid_CallsDataGateway() {
        Request request = new CrudFactoidRequest(Crud.Update, requestableFactoid);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).updateFactoid(factoid);
    }
    
    @Test
    public void updateFactoid_CallsPresenter() {
        Request request = new CrudFactoidRequest(Crud.Update, requestableFactoid);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).factoidUpdated(requestableFactoid);
    }
    
    @Test
    public void deleteFactoid_CallsDataGateway() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Delete, factoidId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).deleteFactoid(factoidId);
    }
    
    @Test
    public void deleteFactoid_CallsPresenter() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Delete, factoidId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).factoidDeleted(factoidId);
    }
}
