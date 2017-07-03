angular.module('insumoApp.services',[]).factory('Insumo',function($resource){
    return $resource('/api/insumos/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});