USE artvision;

-- Inserir setores
INSERT INTO setores (nome_setor, ala) VALUES 
('Restauração', 'A'),
('Conservação', 'B'),
('Exposição', 'C'),
('Administrativo', 'D');

-- Inserir departamentos
INSERT INTO departamentos (nome_depto, id_setor) VALUES 
('Pinturas', 1),
('Esculturas', 1),
('Documentos', 2),
('Fotografias', 2),
('Galeria Principal', 3),
('Galeria Temporária', 3),
('Recursos Humanos', 4),
('Financeiro', 4);

-- Inserir cargos
INSERT INTO cargos (nome_cargo, id_setor) VALUES 
('Restaurador(a) de Pinturas', 1),
('Restaurador(a) de Esculturas', 1),
('Conservador(a) de Documentos', 2),
('Fotógrafo(a) Conservador(a)', 2),
('Curador(a) de Exposições', 3),
('Assistente de Galeria', 3),
('Analista de RH', 4),
('Analista Financeiro', 4); 