###Android Studio 生成aar/jar并导入Android Studio工程中

####aar&jar
aar与jar不同，不像是jar里面只有class文件一样，还有so文件，资源文件和第三方库文件等等，其实就是一个压缩文件。
####生成jar-使用gradle task来生成一个jar文件
在module的build.gradle文件中创建任务，如图：
<img src='img0.png'/>
具体代码：
```javascript
    task makeJar(type: Copy) {
        delete 'build/libs/mysdk.jar'
        from('build/intermediates/bundles/release/')
        into('build/libs/')
        include('classes.jar')
        rename ('classes.jar', 'mysdk.jar')
    }
    makeJar.dependsOn(build)
```
sync(同步)完之后，使用命令行或者使用可视化gradle task来执行上面创建的任务(makeJar)。<br/>
使用可视化任务列表更为可观，<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img0.png"/><img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img1.png"/>
到build/libs/中查看生成的jar
<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img2.png"/>
这个jar就是执行task之后生成的指定name的jar，可以直接使用。下面就是如何使用这个jar。
####导入jar
1.拷贝到工程的libs目录；<br/>
2.build.gradle中的依赖添加 compile files('libs/mysdk.jar')。<br/>
使用可视化工具<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img4.png"/>也可。
####生成aar
要生成 aar 文件，必须将 Module 配置为 library，在 gradle 文件中注明： apply plugin: 'com.android.library'；如图：
<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img5.png"/>
要想apply plugin为library，可以创建Android Library，也可以手动修改apply plugin:为library，一般采用前者方式。
sync之后，在outputs文件夹下会生成相应的aar文件，比jar的方式要简单，不需要创建任务。
<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img6.png"/>
####导入aar
与添加jar类似，首先要拷贝到libs目录下，然后在工程的build.gradle文件中添加依赖，与jar稍有不同的先要添加库，如图，
<img src="https://github.com/ZQiang94/3rdLib2Depend/blob/master/imgs/img7.png"/>，然后在依赖中添加该aar文件
```javascript
compile(name: 'selflibrary-release', ext: 'aar')
```
其中selflibrary-release要与你的libs下的aar文件名称一致。
####小结
Gradle 的一些基本依赖配置方式如下：
compile fileTree(dir: 'xxx', include: ['*.jar', "*.xxx"])：将某个目录下所有符合扩展名的文件作为依赖；<br/>
compile 'com.xx.xx:ProjectName:Version'：配置Maven` 库作为依赖；在 Maven 库中心 可以搜索自己想用的库进行依赖；<br/>
compile project(':AnotherModule')：配置另一个 Module 作为本 Module 的依赖，被依赖的 Module 必须被导入到当前工程中；<br/>
compile files('xxx.jar')：配置某个 jar 包作为依赖。<br/>
aar ： apply plugin: 'com.android.library'；<br/>
apk ：apply plugin: 'com.android.application'