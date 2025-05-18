package br.com.artvision.dto;

public class CargoDTO {

    public CargoDTO(int id, String nome, int idSetor) {
    }

    public class Cargo {
        private int id;
        private String nome;
        private int idSetor;

        public Cargo(int idCargo, String nomeCargo, int idSetor) {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getIdSetor() {
            return idSetor;
        }

        public void setIdSetor(int idSetor) {
            this.idSetor = idSetor;
        }
    }
}
