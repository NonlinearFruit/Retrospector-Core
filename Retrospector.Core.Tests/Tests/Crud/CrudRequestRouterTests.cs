using Retrospector.Core.Boundary;
using Retrospector.Core.Crud;
using Retrospector.Core.Crud.Models;
using Retrospector.Core.Tests.TestDoubles.Boundary;
using Retrospector.Core.Tests.TestDoubles.Crud;
using Retrospector.Core.Tests.Utilities;
using Xunit;

namespace Retrospector.Core.Tests.Tests.Crud
{
    public class CrudRequestRouterTests
    {
        private IRequestRouter _router;
        private CrudUseCase_TestDouble<Media> _mediaUse;
        private CrudUseCase_TestDouble<Review> _reviewUse;
        private CrudUseCase_TestDouble<Factoid> _factoidUse;

        public CrudRequestRouterTests()
        {
            _mediaUse = new CrudUseCase_TestDouble<Media>();
            _reviewUse = new CrudUseCase_TestDouble<Review>();
            _factoidUse = new CrudUseCase_TestDouble<Factoid>();
            _router = new CrudRequestRouter(_mediaUse, _reviewUse, _factoidUse);
        }

        [Fact]
        public void handles_null()
        {
            _router.Disseminate(null);

            Assert.Equal(Verify.Never, _reviewUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _factoidUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _mediaUse.CountOf_Execute_Calls);
        }

        [Fact]
        public void handles_request_of_wrong_type()
        {
            _router.Disseminate(new Request_TestDouble());

            Assert.Equal(Verify.Never, _reviewUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _factoidUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _mediaUse.CountOf_Execute_Calls);
        }

        [Fact]
        public void maps_crud_review_requests_to_review_use_case()
        {
            var request = new CrudRequest<Review>();

            _router.Disseminate(request);

            Assert.Equal(Verify.Once, _reviewUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _factoidUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _mediaUse.CountOf_Execute_Calls);
            Assert.Equal(request, _reviewUse.LastRequestPassedTo_Execute);
        }

        [Fact]
        public void maps_crud_factoid_requests_to_factoid_use_case()
        {
            var request = new CrudRequest<Factoid>();

            _router.Disseminate(request);

            Assert.Equal(Verify.Once, _factoidUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _reviewUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _mediaUse.CountOf_Execute_Calls);
            Assert.Equal(request, _factoidUse.LastRequestPassedTo_Execute);
        }

        [Fact]
        public void maps_crud_media_requests_to_media_use_case()
        {
            var request = new CrudRequest<Media>();

            _router.Disseminate(request);

            Assert.Equal(Verify.Once, _mediaUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _reviewUse.CountOf_Execute_Calls);
            Assert.Equal(Verify.Never, _factoidUse.CountOf_Execute_Calls);
            Assert.Equal(request, _mediaUse.LastRequestPassedTo_Execute);
        }
    }
}