var gulp = require('gulp');

gulp.task('default', ['copy_css', 'copy_js']);

gulp.task('copy_js', function() {
    gulp.src(['bower_components/bootstrap/dist/js/bootstrap.min.js', 'bower_components/jquery/dist/jquery.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
    gulp.src(['bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
    gulp.src(['bower_components/bootstrap-validator/dist/validator.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
});

gulp.task('copy_css', function(){
    gulp.src(['bower_components/bootstrap/dist/css/bootstrap.min.css'])
        .pipe(gulp.dest('web/css/vendor/'));
    gulp.src(['bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css'])
        .pipe(gulp.dest('web/css/vendor/'));
    gulp.src(['bower_components/bootstrap/dist/fonts/*.*'])
        .pipe(gulp.dest('web/css/fonts/'));
});

gulp.task('watch', function(){
    gulp.watch('css/*.css', ['default']);
});
