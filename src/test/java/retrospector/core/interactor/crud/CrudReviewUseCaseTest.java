
package retrospector.core.interactor.crud;

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
import retrospector.core.entity.Review;
import retrospector.core.interactor.crud.CrudRequest.Crud;
import retrospector.core.interactor.crud.CrudReviewRequest;
import retrospector.core.interactor.crud.CrudReviewUseCase;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;
import retrospector.core.utility.TestEntity;
import retrospector.core.datagateway.CrudDataGateway;

@RunWith(MockitoJUnitRunner.class)
public class CrudReviewUseCaseTest {
    
    @Mock
    private CrudDataGateway<Review> dataGateway;
    @Mock
    private CrudPresenter<RequestableReview> presenter;
    private Interactor useCase;
    
    private Review review;
    private RequestableReview requestableReview;
    
    @Before
    public void setUp() {
        useCase = new CrudReviewUseCase(dataGateway, presenter);
        review = TestEntity.getReview();
        requestableReview = EntityConverter.convert(review);
        
        when(dataGateway.add(review)).thenAnswer(i -> review);
        when(dataGateway.get(review.getId())).thenAnswer(i -> review);
        when(dataGateway.update(review)).thenAnswer(i -> review);
    }

    @Test
    public void createReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Create, requestableReview);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).add(review);
    }
    
    @Test
    public void createReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Create, requestableReview);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).added(requestableReview);
    }

    @Test
    public void updateReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Update, requestableReview);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).update(review);
    }    
    
    @Test
    public void updateReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Update, requestableReview);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).updated(requestableReview);
    }
    
    @Test
    public void getReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Read, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).get(requestableReview.getId());
    }
    
    @Test
    public void getReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Read, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(presenter, times(1)).retrieved(requestableReview);
    }
    
    @Test
    public void deleteReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Delete, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).delete(requestableReview.getId());
    }
    
    @Test
    public void deleteReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Delete, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(presenter, times(1)).deleted(requestableReview.getId());
    }
}
