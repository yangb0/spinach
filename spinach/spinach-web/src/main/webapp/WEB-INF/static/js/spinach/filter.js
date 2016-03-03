/**
 * Created by P0015475 on 2015/9/15.
 */
myApp.filter('sexFilter', function () {
    return function (param) {
        if (param == 1) {
            return '男';
        }
        if (param == 0) {
            return '女';
        }
    }
});
//包含html标记的内容,以html展示
myApp.filter('trustHtml', function ($sce) {
    return function (input) {
        return $sce.trustAsHtml(input);
    }
});