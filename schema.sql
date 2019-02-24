--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2019-02-24 23:38:01

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 49344)
-- Name: confirmation_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.confirmation_token (
    token_id integer NOT NULL,
    confirmation_token character varying(255),
    created_date timestamp without time zone,
    user_id bigint
);


ALTER TABLE public.confirmation_token OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 49342)
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.confirmation_token_token_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.confirmation_token_token_id_seq OWNER TO postgres;

--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 200
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.confirmation_token_token_id_seq OWNED BY public.confirmation_token.token_id;


--
-- TOC entry 208 (class 1259 OID 57575)
-- Name: course_resources; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_resources (
    id integer NOT NULL,
    description character varying(255),
    url character varying(255),
    title character varying(255)
);


ALTER TABLE public.course_resources OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 57573)
-- Name: course_resources_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.course_resources_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_resources_id_seq OWNER TO postgres;

--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 207
-- Name: course_resources_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.course_resources_id_seq OWNED BY public.course_resources.id;


--
-- TOC entry 204 (class 1259 OID 57550)
-- Name: course_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.course_type (
    id integer NOT NULL,
    type_name character varying(255)
);


ALTER TABLE public.course_type OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 57570)
-- Name: course_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.course_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.course_type_id_seq OWNER TO postgres;

--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 206
-- Name: course_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.course_type_id_seq OWNED BY public.course_type.id;


--
-- TOC entry 202 (class 1259 OID 57534)
-- Name: courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.courses (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    description character varying(255),
    professor_id bigint,
    course_type integer
);


ALTER TABLE public.courses OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 57547)
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.courses_id_seq OWNER TO postgres;

--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 203
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.courses_id_seq OWNED BY public.courses.id;


--
-- TOC entry 196 (class 1259 OID 32823)
-- Name: id_increment; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_increment
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_increment OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 57586)
-- Name: resources_of_course; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.resources_of_course (
    resource_id integer,
    course_id integer
);


ALTER TABLE public.resources_of_course OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 57557)
-- Name: type_of_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_of_courses (
    type_id integer,
    course_id integer
);


ALTER TABLE public.type_of_courses OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 41150)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id bigint DEFAULT nextval('public.id_increment'::regclass) NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    reg_id bigint NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 32836)
-- Name: user_groups_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_groups_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_groups_seq OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 32833)
-- Name: user_groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_groups (
    id bigint DEFAULT nextval('public.user_groups_seq'::regclass) NOT NULL,
    group_name character varying NOT NULL,
    description character varying NOT NULL
);


ALTER TABLE public.user_groups OWNER TO postgres;

--
-- TOC entry 2729 (class 2604 OID 49347)
-- Name: confirmation_token token_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token ALTER COLUMN token_id SET DEFAULT nextval('public.confirmation_token_token_id_seq'::regclass);


--
-- TOC entry 2732 (class 2604 OID 57578)
-- Name: course_resources id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources ALTER COLUMN id SET DEFAULT nextval('public.course_resources_id_seq'::regclass);


--
-- TOC entry 2731 (class 2604 OID 57572)
-- Name: course_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type ALTER COLUMN id SET DEFAULT nextval('public.course_type_id_seq'::regclass);


--
-- TOC entry 2730 (class 2604 OID 57549)
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- TOC entry 2882 (class 0 OID 49344)
-- Dependencies: 201
-- Data for Name: confirmation_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2889 (class 0 OID 57575)
-- Dependencies: 208
-- Data for Name: course_resources; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2885 (class 0 OID 57550)
-- Dependencies: 204
-- Data for Name: course_type; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2883 (class 0 OID 57534)
-- Dependencies: 202
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2890 (class 0 OID 57586)
-- Dependencies: 209
-- Data for Name: resources_of_course; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2886 (class 0 OID 57557)
-- Dependencies: 205
-- Data for Name: type_of_courses; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2880 (class 0 OID 41150)
-- Dependencies: 199
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- TOC entry 2878 (class 0 OID 32833)
-- Dependencies: 197
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_groups VALUES (1, 'admin', 'admin group');
INSERT INTO public.user_groups VALUES (2, 'user', 'simple user group');
INSERT INTO public.user_groups VALUES (3, 'unconfirmed user', 'group of unconfirmed users');
INSERT INTO public.user_groups VALUES (4, 'professor', 'group of professors');


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 200
-- Name: confirmation_token_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.confirmation_token_token_id_seq', 3, true);


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 207
-- Name: course_resources_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.course_resources_id_seq', 1, false);


--
-- TOC entry 2902 (class 0 OID 0)
-- Dependencies: 206
-- Name: course_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.course_type_id_seq', 1, false);


--
-- TOC entry 2903 (class 0 OID 0)
-- Dependencies: 203
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.courses_id_seq', 1, false);


--
-- TOC entry 2904 (class 0 OID 0)
-- Dependencies: 196
-- Name: id_increment; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_increment', 15, true);


--
-- TOC entry 2905 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_groups_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_groups_seq', 4, true);


--
-- TOC entry 2734 (class 2606 OID 41092)
-- Name: user_groups UG_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_groups
    ADD CONSTRAINT "UG_id" PRIMARY KEY (id);


--
-- TOC entry 2738 (class 2606 OID 49349)
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (token_id);


--
-- TOC entry 2747 (class 2606 OID 57583)
-- Name: course_resources course_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources
    ADD CONSTRAINT course_resources_pkey PRIMARY KEY (id);


--
-- TOC entry 2743 (class 2606 OID 57554)
-- Name: course_type course_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type
    ADD CONSTRAINT course_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2740 (class 2606 OID 57541)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2736 (class 2606 OID 41157)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 1259 OID 57584)
-- Name: course_resources_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_id_uindex ON public.course_resources USING btree (id);


--
-- TOC entry 2748 (class 1259 OID 57585)
-- Name: course_resources_url_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_url_uindex ON public.course_resources USING btree (url);


--
-- TOC entry 2741 (class 1259 OID 57555)
-- Name: course_type_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_id_uindex ON public.course_type USING btree (id);


--
-- TOC entry 2744 (class 1259 OID 57556)
-- Name: course_type_type_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_type_name_uindex ON public.course_type USING btree (type_name);


--
-- TOC entry 2750 (class 2606 OID 49350)
-- Name: confirmation_token confirmation_token_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 2749 (class 2606 OID 41158)
-- Name: user fklthmvyi4mp8c4y133xlmvdfq0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fklthmvyi4mp8c4y133xlmvdfq0 FOREIGN KEY (reg_id) REFERENCES public.user_groups(id);


--
-- TOC entry 2751 (class 2606 OID 57542)
-- Name: courses professor_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT professor_id FOREIGN KEY (id) REFERENCES public."user"(id);


--
-- TOC entry 2754 (class 2606 OID 57589)
-- Name: resources_of_course resources_of_course_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_courses_id_fk FOREIGN KEY (resource_id) REFERENCES public.courses(id);


--
-- TOC entry 2755 (class 2606 OID 57594)
-- Name: resources_of_course resources_of_course_courses_id_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_courses_id_fk_2 FOREIGN KEY (course_id) REFERENCES public.courses(id);


--
-- TOC entry 2752 (class 2606 OID 57560)
-- Name: type_of_courses type_of_courses_course_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_course_type_id_fk FOREIGN KEY (type_id) REFERENCES public.course_type(id);


--
-- TOC entry 2753 (class 2606 OID 57565)
-- Name: type_of_courses type_of_courses_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id);


-- Completed on 2019-02-24 23:38:01

--
-- PostgreSQL database dump complete
--

