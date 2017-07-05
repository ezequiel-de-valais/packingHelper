angular.module('ventaApp.services',[]).factory('Venta',function($resource){
    return $resource('/api/ventas/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});