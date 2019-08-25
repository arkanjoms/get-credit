<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group id="cpfGroup" label="CPF" label-for="cpfInput">
                <b-input id="cpfInput" v-model="form.cpf" type="text" required placeholder="Informe o CPF"/>
            </b-form-group>

            <b-form-group id="nomeGroup" label="Nome" label-for="nomeInput">
                <b-input id="nomeInput" v-model="form.nome" type="text" required placeholder="Informe o nome"/>
            </b-form-group>

            <b-form-group id="idadeGroup" label="Idade" label-for="idadeInput">
                <b-input id="idadeInput" v-model="form.idade" type="number" required placeholder="Informe a idade"
                         min="0"/>
            </b-form-group>

            <b-form-group id="sexoGroup" label="Sexo" label-for="sexoInput">
                <b-form-select id="sexoInput" v-model="form.sexo" :options="sexoOptions" required/>
            </b-form-group>

            <b-form-group id="estadoCivilGroup" label="Estado Civil" label-for="estadoCivilInput">
                <b-form-select id="estadoCivilInput" v-model="form.estadoCivil" :options="estadoCivilOptions" required/>
            </b-form-group>

            <b-form-group id="ufGroup" label="UF" label-for="ufInput">
                <b-input id="ufInput" v-model="form.uf" type="text" required placeholder="Informe a UF"/>
            </b-form-group>

            <b-form-group id="dependentesGroup" label="Dependentes" label-for="dependentesInput">
                <b-input id="dependentesInput" v-model="form.dependentes" type="number" required min="0"
                         placeholder="Informe a quantidade de dependentes"/>
            </b-form-group>

            <b-form-group id="rendaGroup" label="Renda" label-for="rendaInput">
                <b-input id="rendaInput" v-model="form.renda" type="number" min="0.0" step="0.01"
                         placeholder="Informe a renda" class="currency"/>
            </b-form-group>

            <b-button type="submit" variant="primary">Salvar</b-button>
            <b-button type="reset" variant="secondary" v-if="novo">Limpar</b-button>
            <b-button variant="secondary" @click.prevent="$router.go(-1)">Voltar</b-button>
        </b-form>
    </div>
</template>

<script>
    import axios from 'axios'
    import {getCreditApiUrl} from '../../constants'

    export default {
        name: 'Edit',
        data() {
            return {
                form: {},
                novo: true,
                sexoOptions: ['M', 'F'],
                estadoCivilOptions: ['CASADO', 'SOLTEIRO', 'SEPARADO', 'DIVORCIADO', 'VIUVO'],
            }
        },
        mounted() {
            this.cleanForm()
            this.cpf = this.$route.params.cpf
            if (this.cpf) {
                this.novo = false
                this.loadForm(this.cpf)
            }
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
                if (this.novo) {
                    this.create()
                } else {
                    this.update()
                }
            },
            onReset(evt) {
                evt.preventDefault()
                this.cleanForm()
            },
            cleanForm() {
                this.form = {
                    nome: '',
                    idade: undefined,
                    sexo: '',
                    estadoCivil: '',
                    uf: '',
                    dependentes: undefined,
                    renda: 0.0,
                    resultado: '',
                    limite: 0.0,
                }
            },
            async loadForm(cpf) {
                const {data} = await axios.get(`${getCreditApiUrl}/propostas/${cpf}`)
                this.form = {
                    cpf: data.cpf,
                    nome: data.nome,
                    idade: data.idade,
                    sexo: data.sexo,
                    estadoCivil: data.estadoCivil,
                    uf: data.uf,
                    dependentes: data.dependentes,
                    renda: data.renda,
                    resultado: data.resultado,
                    limite: data.limite,
                }
            },
            async create() {
                await axios.post(`${getCreditApiUrl}/propostas`, this.form)
                this.$router.go(-1)
            },
            async update() {
                await axios.put(`${getCreditApiUrl}/propostas/${this.form.cpf}`, this.form)
                this.$router.go(-1)
            },
        },
    }
</script>

<style scoped>
    .currency {
        text-align: right;
    }

    button {
        margin-right: 10px;
    }
</style>
