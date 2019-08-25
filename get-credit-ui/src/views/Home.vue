<template>
    <div class="home">
        <Dashboard msg="Welcome to Your Vue.js App"/>
    </div>
</template>

<script>
    import Dashboard from "../components/Dashboard";

    export default {
        name: 'home',
        components: {
            Dashboard
        }
    }
</script>
