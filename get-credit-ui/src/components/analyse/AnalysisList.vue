<template>
    <div>
        <b-form-group id="cpfFiltroGrupo" label="CPF" label-for="cpfFiltroInput">
            <b-input id="cpfFiltroInput" type="text" v-model="cpfFiltro"/>
        </b-form-group>

        <b-button variant="primary" @click="filtrar">Filtrar</b-button>
        <b-button variant="success" @click="create">Novo</b-button>

        <b-table striped responsive hover :items="analyses" :fields="fields" v-if="analyses && analyses.length > 0">
            <template slot="[actions]" slot-scope="row">
                <b-button size="sm" pill @click="editar(row.item.cpf)" class="mr-1" variant="primary"
                          title="Editar cadastro">
                    <font-awesome-icon icon="edit"/>
                </b-button>
                <b-button size="sm" pill @click="remover(row.item.cpf)" variant="danger" title="Remover">
                    <font-awesome-icon icon="trash"/>
                </b-button>
            </template>
        </b-table>

        <b-card v-else>
            <p>Nenhum registro encontrado.</p>
        </b-card>
    </div>
</template>

<script>
    import axios from 'axios'
    import {getCreditApiUrl, motivoNegativaLabel, resultadoLabel} from '../../constants'

    export default {
        name: 'List',
        data() {
            return {
                cpfFiltro: '',
                fields: [
                    {key: 'cpf', label: 'CPF', formatter: 'formatarCpf'},
                    {key: 'nome', label: 'Nome', sortable: true},
                    {key: 'uf', label: 'UF', sortable: true},
                    {key: 'resultado', label: 'Resultado', formatter: 'formatarResultado'},
                    {key: 'faixa', label: 'Limite', formatter: 'formatarLimite'},
                    {key: 'actions', label: 'Ações'},
                ],
                analyses: [],
            }
        },
        mounted() {
            this.loadAnalysis()
        },
        methods: {
            editar(cpf) {
                this.$router.push({name: 'analyseEdit', params: {cpf}})
            },
            async remover(cpf) {
                await axios.delete(`${getCreditApiUrl}/propostas/${cpf}`)
                this.loadAnalysis()
            },
            create() {
                this.$router.push({name: 'analyseNew'})
            },
            async loadAnalysis() {
                const {data} = await axios.get(`${getCreditApiUrl}/propostas`)
                this.analyses = data
            },
            async filtrar() {
                const cpf = this.cpfFiltro.replace(/\./g, '').replace('-', '')
                const {data} = await axios.get(`${getCreditApiUrl}/propostas`, {params: {cpf}})
                this.analyses = data
            },
            formatarResultado(value) {
                return resultadoLabel[value]
            },
            formatarLimite(value, key, item) {
                if (item.resultado === 'NEGADO') {
                    return motivoNegativaLabel[item.motivoNegativa]
                }

                if (item.faixa.teto === 'null' || !item.faixa.teto) {
                    return `Acima de ${item.faixa.piso}`
                }
                return `Entre ${item.faixa.piso} e ${item.faixa.teto}`
            },
            formatarCpf(value) {
                var str = value + ''
                if (str.length <= 11) {
                    str = str.replace(/\D/g, '')
                    str = str.replace(/(\d{3})(\d)/, '$1.$2')
                    str = str.replace(/(\d{3})(\d)/, '$1.$2')
                    str = str.replace(/(\d{3})(\d{1,2})$/, '$1-$2')
                }
                return str
            },
        },
    }
</script>

<style scoped>
    button {
        margin: 10px;
    }
</style>
