create table consultas(

    id BIGSERIAL not null PRIMARY KEY,
    medico_id BIGSERIAL not null,
    paciente_id BIGSERIAL not null,
    data timestamp without time zone  not null,


    constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

)