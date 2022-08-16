# Ref
1. Convert .rdf into .ttl
https://www.easyrdf.org/converter

2. FOAF (Friend Of A Friend)
https://xmlns.com/foaf/spec/

3. Tạo FOAF bằng GUI (FOAF-a-Matic)
http://ldodds.com/foaf/foaf-a-matic.en.html

4. Ref Github:
https://github.com/SmartDataAnalytics

5. Riot:
https://jena.apache.org/documentation/io/

# Install
1. Download and install Java
- Download:
https://www.oracle.com/java/technologies/downloads/#jdk18-windows
(C:\Program Files\Java\jdk-18.0.2\)
- (Add classPath)
- Confirm:
	java --version
	where java

2. Download and install Apache Jena
- Download:
https://dlcdn.apache.org/jena/binaries/apache-jena-4.5.0.zip
- Giải nén và add vào Classpath cho JENA_HOME
Name: JENA_HOME
Value: D:\Softwares\apache-jena-4.5.0\
- Add Classpath cho riot (để dùng riot ở bất kỳ đâu):
	D:\Softwares\apache-jena-4.5.0\bat
- Confirm
	riot --version
- Chạy lệnh riot:
	riot test.ttl
	riot --formatted=RDF/XML 4.1.ttl

3. Eclipse setting
- Dùng maven để download và quản lý Libs
- Chuyển sang biên dịch với Java version 11 trở lên


# An Introduction to RDF and the Jena RDF API
https://jena.apache.org/getting_started/index.html
https://jena.apache.org/tutorials/rdf_api.html


# Apache fuseki
1. Run
	fuseki-server --update --mem /ds
	fuseki-server --port 8000 --loc=./demo_DB /ds
2. Truy cập:
	http://localhost:3030/
