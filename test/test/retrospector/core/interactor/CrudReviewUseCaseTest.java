
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
import retrospector.core.entity.Review;
import retrospector.core.interactor.CrudReviewRequest.Crud;
import retrospector.core.interactor.CrudReviewRequest;
import retrospector.core.interactor.CrudReviewUseCase;
import retrospector.core.request.model.EntityConverter;
import retrospector.core.request.model.RequestableReview;
import test.retrospector.core.utility.TestEntity;

@RunWith(MockitoJUnitRunner.class)
public class CrudReviewUseCaseTest {
    
    @Mock
    private DataGateway dataGateway;
    @Mock
    private Presenter presenter;
    private Interactor useCase;
    
    private Review review;
    private RequestableReview requestableReview;
    
    @Before
    public void setUp() {
        useCase = new CrudReviewUseCase(dataGateway, presenter);
        review = TestEntity.getReview();
        requestableReview = EntityConverter.convert(review);
        
        when(dataGateway.addReview(review)).thenAnswer(i -> review);
        when(dataGateway.getReview(review.getId())).thenAnswer(i -> review);
        when(dataGateway.updateReview(review)).thenAnswer(i -> review);
    }

    @Test
    public void createReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Create, requestableReview);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).addReview(review);
    }
    
    @Test
    public void createReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Create, requestableReview);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).reviewAdded(requestableReview);
    }

    @Test
    public void updateReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Update, requestableReview);
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).updateReview(review);
    }    
    
    @Test
    public void updateReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Update, requestableReview);
        
        useCase.execute(request);
        
        verify(presenter, times(1)).reviewUpdated(requestableReview);
    }
    
    @Test
    public void getReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Read, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).getReview(requestableReview.getId());
    }
    
    @Test
    public void getReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Read, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(presenter, times(1)).reviewRetrieved(requestableReview);
    }
    
    @Test
    public void deleteReview_CallsDataGateway() {
        Request request = new CrudReviewRequest(Crud.Delete, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(dataGateway, times(1)).deleteReview(requestableReview.getId());
    }
    
    @Test
    public void deleteReview_CallsPresenter() {
        Request request = new CrudReviewRequest(Crud.Delete, requestableReview.getId());
        
        useCase.execute(request);
        
        verify(presenter, times(1)).reviewDeleted(requestableReview.getId());
    }
}
