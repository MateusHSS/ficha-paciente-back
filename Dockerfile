FROM mcr.microsoft.com/mssql/server:2019-latest

ENV SA_PASSWORD=MyStrongPassword123
ENV ACCEPT_EULA=Y

EXPOSE 1433

COPY init-db.sql /docker-entreypoint-initdb.d/

RUN /opt/mssql/bin/sqlservr & sleep 10 \ 
    &&  /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P ${SA_PASSWORD} -i /docker-entreypoint-initdb.d/init-db.sql \
    && pkill sqlservr

CMD ["/opt/mssql/bin/sqlservr"]