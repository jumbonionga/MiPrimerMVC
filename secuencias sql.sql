CREATE DATABASE "Education" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'es_GT.UTF-8' LC_CTYPE = 'es_GT.UTF-8';

CREATE TABLE public.student (
    code integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    gender character varying(1) NOT NULL,
    email character varying(150) NOT NULL,
    contact_phone character varying(50) NOT NULL,
    guardian character varying(50) NOT NULL,
    birthday timestamp(6) with time zone NOT NULL
);

CREATE SEQUENCE public.student_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.student
    ADD CONSTRAINT pk_student PRIMARY KEY (code);

INSERT INTO public.student (code, first_name, last_name, gender, email, contact_phone, guardian, birthday) VALUES (1, 'Fernando Enrique', 'Pérez Gutiérrez', 'M', 'fernando_epg@hotmail.com', '57071052', 'El mismo', '1984-01-03 00:00:00-06');
INSERT INTO public.student (code, first_name, last_name, gender, email, contact_phone, guardian, birthday) VALUES (2, 'Luis Pedro', 'Pérez Gutiérrez', 'M', 'luis.pedro@perezgutierrez.com', '52034433', 'El mismo', '1987-09-03 00:00:00-06');
INSERT INTO public.student (code, first_name, last_name, gender, email, contact_phone, guardian, birthday) VALUES (3, 'Carlos Enrique', 'Pérez Gutiérrez', 'M', 'carlose.perezp@gmail.com', '52057055', 'Esposa', '1954-03-22 00:00:00-06');
INSERT INTO public.student (code, first_name, last_name, gender, email, contact_phone, guardian, birthday) VALUES (4, 'Marcela Patricia', 'Rivera Alegría', 'F', 'marce.rv.al@gmail.com', '59166660', 'Ella misma', '1986-06-06 00:00:00-06');

CREATE TABLE public.course (
    code integer NOT NULL,
    name character varying(150) NOT NULL,
    description character varying(500),
    credits integer NOT NULL
);

CREATE SEQUENCE public.course_code_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

INSERT INTO public.course (code, name, description, credits) VALUES (1, 'Introducción a la Programación', 'Conceptos básicos de programación', 1);
INSERT INTO public.course (code, name, description, credits) VALUES (2, 'Programación  Avanzada', 'Conceptos avanzados de programación', 6);
INSERT INTO public.course (code, name, description, credits) VALUES (3, 'Introducción a Bases de Datos', 'Conceptos básicos sobre bases de datos', 4);
