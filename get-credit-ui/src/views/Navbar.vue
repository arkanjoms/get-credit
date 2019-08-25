<template>
    <b-navbar toggleable="lg" type="dark" variant="info" id="navbar">
        <b-navbar-brand href="#">GetCredit</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item to="/" >Home</b-nav-item>
                <b-nav-item to="/analysis" >Analises</b-nav-item>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script>
    export default {
        name: "Navbar"
    }
</script>

<style scoped>

</style>