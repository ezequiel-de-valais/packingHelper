angular.module('productoApp.services',[]).factory('Producto',function($resource){
    return $resource('/api/productos/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});