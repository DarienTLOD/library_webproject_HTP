var gulp = require('gulp');

var cssmin = require('gulp-cssmin');
var uglify = require('gulp-uglify');
var rename = require('gulp-rename');
var uncss = require('gulp-uncss');

gulp.task('default', ['copy_css', 'copy_js','scripts','styles']);

gulp.task('copy_js', function () {
    gulp.src(['bower_components/bootstrap/dist/js/bootstrap.min.js', 'bower_components/jquery/dist/jquery.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
    gulp.src(['bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
    gulp.src(['bower_components/bootstrap-validator/dist/validator.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
    gulp.src(['bower_components/bootstrap-select/dist/js/bootstrap-select.min.js'])
        .pipe(gulp.dest('web/js/vendor/'));
});

gulp.task('copy_css', function () {
    gulp.src(['bower_components/bootstrap/dist/css/bootstrap.min.css'])
        .pipe(gulp.dest('web/css/vendor/'));
    gulp.src(['bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker3.min.css'])
        .pipe(gulp.dest('web/css/vendor/'));
    gulp.src(['bower_components/bootstrap-select/dist/css/bootstrap-select.min.css'])
        .pipe(gulp.dest('web/css/vendor/'));
    gulp.src(['bower_components/bootstrap/dist/fonts/*.*'])
        .pipe(gulp.dest('web/css/fonts/'));
});

gulp.task('watch', function () {
    gulp.watch('css/*.css', ['default']);
});

gulp.task('styles', function() {
   gulp.src(['web/css/*.css','!web/css/*.min.css'])
        .pipe(uncss({html: ['html_templates/*.html']
        }))
        .pipe(cssmin())
        .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('web/css/'));
});

gulp.task('scripts', function() {
    return gulp.src(['web/js/*.js','!web/js/*.min.js'])
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(gulp.dest('web/js/'));
    });

