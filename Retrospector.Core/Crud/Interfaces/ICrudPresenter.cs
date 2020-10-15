using System.Collections.Generic;

namespace Retrospector.Core.Crud.Interfaces
{
    public interface ICrudPresenter<T> where T : IModel
    {
        public void Added(T entity);
        public void Retrieved(T entity);
        public void Updated(T entity);
        public void Deleted(int id);
        public void RetrievedAll(IEnumerable<T> entities);
        public void RetrievedAllByMediaId(IEnumerable<T> entities);
    }
}