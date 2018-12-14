
package test.retrospector.core.interactor.crud;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.CrudPresenter;
import retrospector.core.boundry.Interactor;
import retrospector.core.boundry.Request;
import retrospector.core.entity.Factoid;
import retrospector.core.interactor.crud.CrudFactoidRequest;
import retrospector.core.interactor.crud.CrudFactoidUseCase;
import retrospector.core.interactor.crud.CrudRequest.Crud;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableFactoid;
import test.retrospector.core.utility.TestEntity;
import retrospector.core.datagateway.CrudDataGateway;

@RunWith(MockitoJUnitRunner.class)
public class CrudFactoidUseCaseTest {
    
    @Mock
    private CrudDataGateway<Factoid> dataGateway;
    @Mock
    private CrudPresenter<RequestableFactoid> presenter;
    private Interactor useCase;
    
    private Factoid factoid;
    private RequestableFactoid requestableFactoid;
    
    @Before
    public void setUp() {
        useCase = new CrudFactoidUseCase(dataGateway, presenter);
        factoid = TestEntity.getFactoid();
        requestableFactoid = EntityConverter.convert(factoid);
        
        when(dataGateway.add(factoid)).thenAnswer(i -> factoid);
        when(dataGateway.get(factoid.getId())).thenAnswer(i -> factoid);
        when(dataGateway.update(factoid)).thenAnswer(i -> factoid);
    }
    
    @Test
    public void addFactoid_CallsDataGateway() {
        Request request = new CrudFactoidRequest(CrudFactoidRequest.Crud.Create, requestableFactoid);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).add(factoid);
    }
        
    @Test
    public void addFactoid_CallsPresenter() {
        Request request = new CrudFactoidRequest(Crud.Create, requestableFactoid);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).added(requestableFactoid);
    }
    
    @Test
    public void readFactoid_CallsDataGateway() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Read, factoidId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).get(factoidId);
    }
    
    @Test
    public void readFactoid_CallsPresenter() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Read, factoidId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).retrieved(requestableFactoid);
    }
    
    @Test
    public void updateFactoid_CallsDataGateway() {
        Request request = new CrudFactoidRequest(Crud.Update, requestableFactoid);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).update(factoid);
    }
    
    @Test
    public void updateFactoid_CallsPresenter() {
        Request request = new CrudFactoidRequest(Crud.Update, requestableFactoid);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).updated(requestableFactoid);
    }
    
    @Test
    public void deleteFactoid_CallsDataGateway() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Delete, factoidId);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).delete(factoidId);
    }
    
    @Test
    public void deleteFactoid_CallsPresenter() {
        int factoidId = factoid.getId();
        Request request = new CrudFactoidRequest(Crud.Delete, factoidId);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).deleted(factoidId);
    }
}
