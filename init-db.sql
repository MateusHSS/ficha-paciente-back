USE master;
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'ficha_paciente_db')
BEGIN
  CREATE DATABASE ficha_paciente_db;
END
GO

USE ficha_paciente_db;
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'especialidade')
BEGIN
  CREATE TABLE especialidade (
    id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL
  );
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'plano_saude')
BEGIN
  CREATE TABLE plano_saude (
    id UNIQUEIDENTIFIER PRIMARY KEY NOT NULL,
    nome VARCHAR(50) NOT NULL
  );
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'ficha_paciente')
BEGIN
  CREATE TABLE ficha_paciente (
    id_especialidade UNIQUEIDENTIFIER FOREIGN KEY REFERENCES especialidade(id) NOT NULL,
    id_plano_saude UNIQUEIDENTIFIER FOREIGN KEY REFERENCES plano_saude(id) NOT NULL,
    numero_carteira_plano VARCHAR(20) NOT NULL,
    nome_paciente VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_especialidade, id_plano_saude, numero_carteira_plano)
  );
END
GO