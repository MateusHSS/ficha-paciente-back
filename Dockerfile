FROM mcr.microsoft.com/mssql/server:2019-latest

ENV SA_PASSWORD=MyStrongPassword123
ENV ACCEPT_EULA=Y

RUN /opt/mssql/bin/sqlservr & sleep 10 \
    && /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P $SA_PASSWORD -Q "CREATE DATABASE ficha_paciente_db" \
    && pkill sqlservr

EXPOSE 1433

CMD ["/opt/mssql/bin/sqlservr"]