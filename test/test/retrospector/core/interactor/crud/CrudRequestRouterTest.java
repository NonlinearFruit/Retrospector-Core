package test.retrospector.core.interactor.crud;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import retrospector.core.boundry.Request;
import retrospector.core.boundry.RequestRouter;
import retrospector.core.interactor.crud.CrudFactoidRequest;
import retrospector.core.interactor.crud.CrudFactoidUseCase;
import retrospector.core.interactor.crud.CrudMediaRequest;
import retrospector.core.interactor.crud.CrudMediaUseCase;
import retrospector.core.interactor.crud.CrudRequest;
import retrospector.core.interactor.crud.CrudReviewRequest;
import retrospector.core.interactor.crud.CrudReviewUseCase;
import retrospector.core.request.model.RequestableFactoid;
import retrospector.core.request.model.RequestableMedia;
import retrospector.core.request.model.RequestableReview;
import retrospector.core.interactor.crud.CrudRequestRouter;

@RunWith(MockitoJUnitRunner.class)
public class CrudRequestRouterTest {
    @Mock
    private CrudMediaUseCase mediaUse;
    @Mock
    private CrudReviewUseCase reviewUse;
    @Mock
    private CrudFactoidUseCase factoidUse;
    private RequestRouter router;
    
    @Before
    public void setup() {
        router  = new CrudRequestRouter(mediaUse, reviewUse, factoidUse);
    }
    
    @Test
    public void disseminate_WhenMediaRequest_CallsMediaUseCase() {
        Request request = new CrudMediaRequest(CrudRequest.Crud.Create, getRequestableMedia());
        
        router.disseminate(request);
        
        verify(mediaUse, times(1)).execute(request);
    }
        
    @Test
    public void disseminate_WhenReviewRequest_CallsReviewUseCase() {
        Request request = new CrudReviewRequest(CrudRequest.Crud.Create, getRequestableReview());
        
        router.disseminate(request);
        
        verify(reviewUse, times(1)).execute(request);
    }
        
    @Test
    public void disseminate_WhenFactoidRequest_CallsFactoidUseCase() {
        Request request = new CrudFactoidRequest(CrudRequest.Crud.Create, getRequestableFactoid());
        
        router.disseminate(request);
        
        verify(factoidUse, times(1)).execute(request);
    }
    
    private RequestableMedia getRequestableMedia() {
        RequestableMedia media = new RequestableMedia("Title", "Creative Creator", "Book");
        return media;
    }
    
    private RequestableReview getRequestableReview() {
        RequestableReview review = new RequestableReview(1, LocalDate.now(), "Ben", "Not good");
        return review;
    }
    
    private RequestableFactoid getRequestableFactoid() {
        RequestableFactoid factoid = new RequestableFactoid("Genre", "Mystery");
        return factoid;
    }
}
