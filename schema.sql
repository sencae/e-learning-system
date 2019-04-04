--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2019-04-05 02:26:31

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "e-learning-system";
--
-- TOC entry 2945 (class 1262 OID 32819)
-- Name: e-learning-system; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "e-learning-system" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE "e-learning-system" OWNER TO postgres;


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 216 (class 1255 OID 65833)
-- Name: create_user_function(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.create_user_function() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ 
BEGIN
	INSERT INTO user_info(user_id) VALUES (new.ID);
    RETURN NEW;
END;
$$;


ALTER FUNCTION public.create_user_function() OWNER TO postgres;

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
-- TOC entry 2946 (class 0 OID 0)
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
    url character varying(255),
    title character varying(255),
    topic_id bigint NOT NULL
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
-- TOC entry 2947 (class 0 OID 0)
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
-- TOC entry 2948 (class 0 OID 0)
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
    professor_id bigint NOT NULL,
    start_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
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
-- TOC entry 2949 (class 0 OID 0)
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
    course_id integer,
    id integer NOT NULL
);


ALTER TABLE public.resources_of_course OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 65835)
-- Name: resources_of_course_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.resources_of_course_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.resources_of_course_id_seq OWNER TO postgres;

--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 211
-- Name: resources_of_course_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.resources_of_course_id_seq OWNED BY public.resources_of_course.id;


--
-- TOC entry 213 (class 1259 OID 65856)
-- Name: topics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topics (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    course_id bigint
);


ALTER TABLE public.topics OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 65854)
-- Name: topics_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.topics_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topics_id_seq OWNER TO postgres;

--
-- TOC entry 2951 (class 0 OID 0)
-- Dependencies: 212
-- Name: topics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.topics_id_seq OWNED BY public.topics.id;


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
-- TOC entry 210 (class 1259 OID 57627)
-- Name: user_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_info (
    user_id bigint NOT NULL,
    avatar_url character varying(255),
    university character varying(255),
    brief_information character varying(255)
);


ALTER TABLE public.user_info OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 65876)
-- Name: users_on_courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_on_courses (
    id bigint NOT NULL,
    user_id bigint,
    course_id bigint
);


ALTER TABLE public.users_on_courses OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 65874)
-- Name: users_on_courses_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_on_courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_on_courses_id_seq OWNER TO postgres;

--
-- TOC entry 2952 (class 0 OID 0)
-- Dependencies: 214
-- Name: users_on_courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_on_courses_id_seq OWNED BY public.users_on_courses.id;


--
-- TOC entry 2749 (class 2604 OID 49347)
-- Name: confirmation_token token_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token ALTER COLUMN token_id SET DEFAULT nextval('public.confirmation_token_token_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 57578)
-- Name: course_resources id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources ALTER COLUMN id SET DEFAULT nextval('public.course_resources_id_seq'::regclass);


--
-- TOC entry 2753 (class 2604 OID 57572)
-- Name: course_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type ALTER COLUMN id SET DEFAULT nextval('public.course_type_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 57549)
-- Name: courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses ALTER COLUMN id SET DEFAULT nextval('public.courses_id_seq'::regclass);


--
-- TOC entry 2755 (class 2604 OID 65837)
-- Name: resources_of_course id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course ALTER COLUMN id SET DEFAULT nextval('public.resources_of_course_id_seq'::regclass);


--
-- TOC entry 2756 (class 2604 OID 65859)
-- Name: topics id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics ALTER COLUMN id SET DEFAULT nextval('public.topics_id_seq'::regclass);


--
-- TOC entry 2757 (class 2604 OID 65879)
-- Name: users_on_courses id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses ALTER COLUMN id SET DEFAULT nextval('public.users_on_courses_id_seq'::regclass);





--
-- TOC entry 2921 (class 0 OID 32833)
-- Dependencies: 197
-- Data for Name: user_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_groups VALUES (1, 'admin', 'admin group');
INSERT INTO public.user_groups VALUES (2, 'user', 'simple user group');
INSERT INTO public.user_groups VALUES (3, 'unconfirmed user', 'group of unconfirmed users');
INSERT INTO public.user_groups VALUES (4, 'professor', 'group of professors');
INSERT INTO public.user_groups VALUES (5, 'student', 'group of students');



--
-- TOC entry 2759 (class 2606 OID 41092)
-- Name: user_groups UG_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_groups
    ADD CONSTRAINT "UG_id" PRIMARY KEY (id);


--
-- TOC entry 2763 (class 2606 OID 49349)
-- Name: confirmation_token confirmation_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_pkey PRIMARY KEY (token_id);


--
-- TOC entry 2772 (class 2606 OID 57583)
-- Name: course_resources course_resources_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources
    ADD CONSTRAINT course_resources_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 57554)
-- Name: course_type course_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_type
    ADD CONSTRAINT course_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2765 (class 2606 OID 57541)
-- Name: courses courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2776 (class 2606 OID 65843)
-- Name: resources_of_course resources_of_course_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_pk PRIMARY KEY (id);


--
-- TOC entry 2782 (class 2606 OID 65861)
-- Name: topics topics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_pkey PRIMARY KEY (id);


--
-- TOC entry 2779 (class 2606 OID 57634)
-- Name: user_info user_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_info
    ADD CONSTRAINT user_info_pkey PRIMARY KEY (user_id);


--
-- TOC entry 2761 (class 2606 OID 41157)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2785 (class 2606 OID 65881)
-- Name: users_on_courses users_on_courses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 1259 OID 57584)
-- Name: course_resources_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_id_uindex ON public.course_resources USING btree (id);


--
-- TOC entry 2773 (class 1259 OID 57585)
-- Name: course_resources_url_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_resources_url_uindex ON public.course_resources USING btree (url);


--
-- TOC entry 2766 (class 1259 OID 57555)
-- Name: course_type_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_id_uindex ON public.course_type USING btree (id);


--
-- TOC entry 2769 (class 1259 OID 57556)
-- Name: course_type_type_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX course_type_type_name_uindex ON public.course_type USING btree (type_name);


--
-- TOC entry 2774 (class 1259 OID 65841)
-- Name: resources_of_course_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX resources_of_course_id_uindex ON public.resources_of_course USING btree (id);


--
-- TOC entry 2780 (class 1259 OID 65867)
-- Name: topics_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX topics_id_uindex ON public.topics USING btree (id);


--
-- TOC entry 2777 (class 1259 OID 57640)
-- Name: user_info_avatar_url_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX user_info_avatar_url_uindex ON public.user_info USING btree (avatar_url);


--
-- TOC entry 2783 (class 1259 OID 65892)
-- Name: users_on_courses_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX users_on_courses_id_uindex ON public.users_on_courses USING btree (id);


--
-- TOC entry 2798 (class 2620 OID 65834)
-- Name: user create_user_trigger; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER create_user_trigger AFTER INSERT ON public."user" FOR EACH ROW EXECUTE PROCEDURE public.create_user_function();


--
-- TOC entry 2787 (class 2606 OID 49350)
-- Name: confirmation_token confirmation_token_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.confirmation_token
    ADD CONSTRAINT confirmation_token_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- TOC entry 2791 (class 2606 OID 65869)
-- Name: course_resources course_resources_topics_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.course_resources
    ADD CONSTRAINT course_resources_topics_id_fk FOREIGN KEY (topic_id) REFERENCES public.topics(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2786 (class 2606 OID 41158)
-- Name: user fklthmvyi4mp8c4y133xlmvdfq0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fklthmvyi4mp8c4y133xlmvdfq0 FOREIGN KEY (reg_id) REFERENCES public.user_groups(id);


--
-- TOC entry 2788 (class 2606 OID 57604)
-- Name: courses professor_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.courses
    ADD CONSTRAINT professor_id FOREIGN KEY (professor_id) REFERENCES public."user"(id);


--
-- TOC entry 2792 (class 2606 OID 65844)
-- Name: resources_of_course resources_of_course_course_resources_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_course_resources_id_fk FOREIGN KEY (resource_id) REFERENCES public.course_resources(id) ON DELETE CASCADE;


--
-- TOC entry 2793 (class 2606 OID 65849)
-- Name: resources_of_course resources_of_course_courses_id_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.resources_of_course
    ADD CONSTRAINT resources_of_course_courses_id_fk_2 FOREIGN KEY (course_id) REFERENCES public.courses(id) ON DELETE CASCADE;


--
-- TOC entry 2795 (class 2606 OID 65862)
-- Name: topics topics_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topics
    ADD CONSTRAINT topics_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2789 (class 2606 OID 57560)
-- Name: type_of_courses type_of_courses_course_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_course_type_id_fk FOREIGN KEY (type_id) REFERENCES public.course_type(id);


--
-- TOC entry 2790 (class 2606 OID 57565)
-- Name: type_of_courses type_of_courses_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_of_courses
    ADD CONSTRAINT type_of_courses_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id);


--
-- TOC entry 2794 (class 2606 OID 57641)
-- Name: user_info user_info_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_info
    ADD CONSTRAINT user_info_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- TOC entry 2797 (class 2606 OID 65887)
-- Name: users_on_courses users_on_courses_courses_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_courses_id_fk FOREIGN KEY (course_id) REFERENCES public.courses(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2796 (class 2606 OID 65882)
-- Name: users_on_courses users_on_courses_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_on_courses
    ADD CONSTRAINT users_on_courses_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-04-05 02:26:31

--
-- PostgreSQL database dump complete
--

