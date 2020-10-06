
#### Internationalization

##### Configuration
-LocalResolver
	-Default Locale -Locale.US
-ResourceBundleMessageSource

##### Usage
-Autowire MessageSource
-@RequestHeader(value= "Accept-Language", Required=false) Locale locale
-messageSource.getMessage("helloWorld.message",null,message)