module.exports = function(grunt) {

    // Example
    // https://gist.github.com/eliotfowler/a6d1bcbe15ec3f9ee49b

    // Load grunt tasks automatically
    require('load-grunt-tasks')(grunt);

    // Time how long tasks take. Can help when optimizing build times
    require('time-grunt')(grunt);

    // Define the configuration for all the tasks
    grunt
	    .initConfig({

		pkg : grunt.file.readJSON('package.json'),

		distdir : 'dist',

		src : {
		    html : [ 'index.html' ],
		    angular : {
			    general : [ 'app' ],
			    views : [ 'views' ]
		    },
		    dependencies : {
			    bower : [ 'bower_components' ]
		    },
		    assets : {
			    general : [ 'assets' ],
			    img : [ 'assets/img' ],
			    fonts : [ 'assets/fonts' ],
			    css : [ 'assets/css' ]
		    }
		},

		// Automatically inject Bower components into the app
		bowerInstall : {
		    target : {
			    src : [ 'index.html' ],
                exclude : [
                    'bower_components/angular/angular.min.js',
                    'bower_components/angular-route/angular-route.min.js',
                    'bower_components/bootstrap/<%= distdir %>/js/bootstrap.min.js',
                    'bower_components/jquery/<%= distdir %>/jquery.min.js',
                    'bower_components/chart.js/<%= distdir %>/Chart.min.js'
                ]
		    }
		},

		clean : [ '<%= distdir %>', '.tmp' ],

		// Copies remaining files to places other tasks can use
		copy : {
		    html : {
			    src : '<%= src.html %>',
			    dest : '<%= distdir %>/<%= src.html %>'
		    },
		    angular : {
			    expand : true,
			    cwd : '<%= src.angular.general %>/',
			    dest : '<%= distdir %>/app',
			    src : [ '**/*.js' ]
		    },
		    bower : {
			    expand : true,
			    cwd : '<%= src.dependencies.bower %>/',
			    dest : '<%= distdir %>/bower_components',
			    src : [ '**/*.*' ]
		    },
		    assets : {
			    expand : true,
			    cwd : '<%= src.assets.general %>/',
			    dest : '<%= distdir %>/assets',
			    src : '**/*.{png,jpg,jpeg,gif,css}'
		    },
		    views : {
			    expand : true,
			    cwd : '<%= src.angular.views %>/',
			    dest : '<%= distdir %>/views',
			    src : '**/*.html'
		    }
		},

		useminPrepare : {
		    html : '<%= src.html %>'
		},

		usemin : {
		    html : [ '<%= distdir %>/<%= src.html %>' ]
		},

		targethtml : {
		    dist : {
                files : {
                    '<%= distdir %>/<%= src.html %>' : '<%= distdir %>/<%= src.html %>'
                }
		    }
		},

		compress : {
		    main : {
                options : {
                    mode : 'tgz',
                    archive : 'target/iStat.com.tgz'
                },
                files : [ {
                    expand : true,
                    src : '**/*',
                    cwd : '<%= distdir %>/',
                    dot : true
                } ]
		    }
		}
	    });

    grunt.registerTask('build', [ 'clean', 'bowerInstall', 'copy',
	    'useminPrepare', 'concat', 'uglify', 'usemin' ]);

    grunt.registerTask('cibuild', [ 'build', 'compress' ]);

};